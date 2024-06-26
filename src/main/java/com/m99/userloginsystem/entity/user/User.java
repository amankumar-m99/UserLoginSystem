package com.m99.userloginsystem.entity.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.m99.userloginsystem.entity.role.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
	/**
	 *
	 */
	private static final long serialVersionUID = -8703384705177232674L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String email;
	private String password;
	private Boolean isLocked;
	private Boolean isEnabled;
	private Boolean isAccountExpired;
	private Boolean isCredentialExpired;
	@OneToOne
	private UserPersonalDetails personalDetails;
	@OneToOne
	private UserSecurityDetails securityDetails;

	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
//	@JoinTable(name = "USER_ROLE")
	@JoinTable(name = "USER_ROLE", joinColumns = {@JoinColumn(name="USER_ID")}, inverseJoinColumns = {@JoinColumn(name="ROLE_ID")})
	private Set<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		getRoles().forEach(role->{
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		});
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !(getIsAccountExpired());
	}

	@Override
	public boolean isAccountNonLocked() {
		return !(getIsLocked());
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !(getIsCredentialExpired());
	}

	@Override
	public boolean isEnabled() {
		return getIsEnabled();
	}
}
