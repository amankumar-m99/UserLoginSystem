package com.m99.userloginsystem.service.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.m99.userloginsystem.configuration.data.StaticData;
import com.m99.userloginsystem.customexception.email.EmailAlreadyExistsException;
import com.m99.userloginsystem.customexception.email.EmailNotVerifiedException;
import com.m99.userloginsystem.customexception.images.ImageResourceNotFoundException;
import com.m99.userloginsystem.customexception.user.UserNameNotAvailableException;
import com.m99.userloginsystem.dao.profilepic.ProfilePicDao;
import com.m99.userloginsystem.dao.role.RoleDao;
import com.m99.userloginsystem.dao.security.EmailSecurityCodeDao;
import com.m99.userloginsystem.dao.user.UserDao;
import com.m99.userloginsystem.dao.user.UserPersonalDetailsDao;
import com.m99.userloginsystem.dao.user.UserSecurityDetailsDao;
import com.m99.userloginsystem.entity.profilepic.ProfilePic;
import com.m99.userloginsystem.entity.role.Role;
import com.m99.userloginsystem.entity.security.EmailSecurityCode;
import com.m99.userloginsystem.entity.user.User;
import com.m99.userloginsystem.entity.user.UserPersonalDetails;
import com.m99.userloginsystem.entity.user.UserSecurityDetails;
import com.m99.userloginsystem.entity.user.profilepic.UserProfilePicResource;
import com.m99.userloginsystem.model.user.UserForm;
import com.m99.userloginsystem.utils.enums.Gender;

@Service
public class UserService {

	@Autowired
	private EmailSecurityCodeDao emailSecurityCodeDao;

	@Autowired
	private ProfilePicDao profilePicDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserPersonalDetailsDao userPersonalDetailsDao;

	@Autowired
	private UserSecurityDetailsDao userSecurityDetailsDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getAllUsers(){
		return userDao.findAll();
	}

	public User getUserByUsername(String username) {
		User user = userDao.findByUsername(username).orElse(null);
		return user;
	}

	public User getUserById(long id) {
		User user = userDao.findById(id).orElse(null);
		return user;
	}

	public User registerUser(UserForm userForm) {
		if(!isUserAvailable(userForm.getEmail(), UserLookupType.EMAIL)) {
			throw new EmailAlreadyExistsException("email already exists!");
		}
		EmailSecurityCode emailSecurityCode = emailSecurityCodeDao.findByEmail(userForm.getEmail()).orElse(null);
		if(emailSecurityCode != null) {
			if(!emailSecurityCode.getIsUsed())
				throw new EmailNotVerifiedException("email "+userForm.getEmail()+" is not verified.");
		}
		else {
			throw new EmailNotVerifiedException("email "+userForm.getEmail()+" is not verified.");
		}
		if(!isUserAvailable(userForm.getUsername(), UserLookupType.USERNAME)) {
			throw new UserNameNotAvailableException("username already exists!");
		}
		User user = createUserFromUserForm(userForm);
		return userDao.save(user);
	}

	public String saveUserProfilePic(long id, MultipartFile multipartFile) {
		String applicationDataDirectory = StaticData.getApplicationDataDirectory();
		String profilePicsDirectory = applicationDataDirectory + File.separator + "ProfilePics";
		File file = new File(profilePicsDirectory);
		if(!file.exists())
			file.mkdirs();
		try {
			ProfilePic profilePic = profilePicDao.findByUserId(id).orElse(null);
			if(profilePic != null) {
				updateProfilePic(multipartFile, profilePic, id);
			}
			else {
				String targetFileName = id+"_"+multipartFile.getOriginalFilename();
				InputStream inputStream = multipartFile.getInputStream();
				byte data[] = new byte[inputStream.available()];
				inputStream.read(data);
				FileOutputStream fileOutputStream = new FileOutputStream(profilePicsDirectory+File.separator+ targetFileName);
				fileOutputStream.write(data);
				fileOutputStream.flush();
				fileOutputStream.close();
				inputStream.close();
				profilePic = ProfilePic.builder().userId(id).imageName(targetFileName).location(profilePicsDirectory).build();
				profilePicDao.save(profilePic);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "/images/profile-pic/"+id;
	}

	private void updateProfilePic(MultipartFile multipartFile, ProfilePic profilePic, long id) throws IOException {
		String oldFilePath = profilePic.getLocation()+File.separator+profilePic.getImageName();
		File oldFile = new File(oldFilePath);
		File newRenamedFile = new File(profilePic.getLocation()+File.separator+ id+ "_"+multipartFile.getOriginalFilename());
		InputStream inputStream = multipartFile.getInputStream();
		byte data[] = new byte[inputStream.available()];
		inputStream.read(data);
		FileOutputStream fileOutputStream = new FileOutputStream(oldFile);
		fileOutputStream.write(data);
		fileOutputStream.flush();
		fileOutputStream.close();
		inputStream.close();
		oldFile.renameTo(newRenamedFile);
	}

	public UserProfilePicResource getUserProfilePicResource(long id) throws ImageResourceNotFoundException {
		ProfilePic profilePic = profilePicDao.findByUserId(id).orElse(null);
		if(profilePic==null)
			throw new ImageResourceNotFoundException("no resource found for this account");
		String profilePicsDirectory = profilePic.getLocation()+ File.separator + profilePic.getImageName();
		File file = new File(profilePicsDirectory);
		if(!file.exists())
			throw new ImageResourceNotFoundException("no resource found for this account");
		return new UserProfilePicResource(file);
	}

	public UserProfilePicResource getFirstProfilePicResource() throws ImageResourceNotFoundException {
		String profilePicsDirectory = StaticData.getApplicationDataDirectory()+File.separator+"TestImages";
		File file = new File(profilePicsDirectory);
		if(!file.exists())
			throw new ImageResourceNotFoundException("no resource found for this account");
		File[] listFiles = file.listFiles();
		return new UserProfilePicResource(listFiles[0]);
	}

	private User createUserFromUserForm(UserForm userForm) {
		UserPersonalDetails personalDetails = UserPersonalDetails.builder()
				.firstName("N/A")
				.middleName("N/A")
				.lastName("N/A")
				.phoneNumber("9876543210")
				.gender(Gender.MALE)
				.country("India")
				.dateOfBirth(new Date())
				.build();
		personalDetails = userPersonalDetailsDao.save(personalDetails);
		UserSecurityDetails securityDetails = UserSecurityDetails.builder()
				.recoveryEmail("N/A")
				.recoveryPhoneNumber("N/A")
				.securityQuestion("N/A")
				.securityAnswer("N/A")
				.loginAlert(true)
				.passwordUpdateAlert(true)
				.twoStepLogin(true)
				.build();
		securityDetails = userSecurityDetailsDao.save(securityDetails);
		User user = User.builder()
				.username(userForm.getUsername())
				.email(userForm.getEmail())
				.password(passwordEncoder.encode(userForm.getPassword()))
				.roles(getRolesFromIds(userForm.getRoles()))
				.isLocked(false)
				.isEnabled(true)
				.isAccountExpired(false)
				.isCredentialExpired(false)
				.personalDetails(personalDetails)
				.securityDetails(securityDetails)
				.build();
		if(user.getRoles() == null || user.getRoles().size() == 0) {
			Role role = roleDao.findByRoleName("user").get();
			if(role!=null) {
				Set<Role> roles = new HashSet<Role>();
				roles.add(role);
				user.setRoles(roles);
			}
		}
		return user;
	}

	private Set<Role> getRolesFromIds(Set<Integer> roleIds){
		Set<Role> roles = new HashSet<>();
		roleIds.forEach(i->{
			roles.add(roleDao.findById(i).get());
		});
		return roles;
	}

	private boolean isUserAvailable(String key, UserLookupType lookupType) {
		User user = null;
		try {
			switch (lookupType) {
			case EMAIL:
				user = userDao.findByEmail(key).get();
				break;
			case USERNAME:
				user = userDao.findByUsername(key).get();
				break;
			}
		} catch(NoSuchElementException e) {
			return true;
		}
		if(user != null)
			return false;
		return true;
	}

	public void initRolesAndUsers() {
		initRoles();
		initUsers();
	}

	private void initRoles() {
		Role superAdminRole = Role.builder().roleName("superadmin").roleDescription("The supreme admin").build();
		roleDao.save(superAdminRole);
		Role adminRole = Role.builder().roleName("admin").roleDescription("The admin").build();
		roleDao.save(adminRole);
		Role userRole = Role.builder().roleName("user").roleDescription("The default user").build();
		roleDao.save(userRole);
	}

	private void initUsers(){
		UserForm superAdminUserForm = UserForm.builder()
				.username("superAdmin")
				.email("superadmin@m99.com")
				.password("1234")
				.roles(Arrays.asList(1).stream().collect(Collectors.toSet()))
				.build();
		userDao.save(createUserFromUserForm(superAdminUserForm));

		UserForm adminUserForm = UserForm.builder()
				.username("admin")
				.email("admin@m99.com")
				.password("1234")
				.roles(Arrays.asList(2).stream().collect(Collectors.toSet()))
				.build();
		userDao.save(createUserFromUserForm(adminUserForm));

		UserForm userForm = UserForm.builder()
				.username("amank")
				.email("amankumar@m99.com")
				.password("1234")
				.roles(Arrays.asList(3).stream().collect(Collectors.toSet()))
				.build();
		userDao.save(createUserFromUserForm(userForm));
	}
}
