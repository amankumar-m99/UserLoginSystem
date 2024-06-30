package com.m99.userloginsystem.service.smtp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.m99.userloginsystem.customexception.smtp.NoSuchSmtpException;
import com.m99.userloginsystem.dao.smtp.SmtpDao;
import com.m99.userloginsystem.entity.smtp.Smtp;

//@Service
@Component
public class SmtpService {

	@Autowired
	private SmtpDao smtpDao;

	public Smtp add(Smtp smtp) {
		if(smtp.getIsSelected())
			smtpDao.markAllUnSelected();
		return smtpDao.save(smtp);
	}

	public Smtp edit(Smtp smtp) {
		return smtpDao.save(smtp);
	}

	public Smtp getById(long id) throws NoSuchSmtpException {
		return smtpDao.findById(id).orElseThrow(()->new NoSuchSmtpException("No smtp exists with id "+ id));
	}

	public List<Smtp> getAllSmtp(){
		return smtpDao.findAll();
	}

	public void markSelected(long id) {
		smtpDao.markAllUnSelected();
		smtpDao.markSelected(id);
	}

	public Smtp deleteById(int id) throws NoSuchSmtpException {
		Smtp smtp = getById(id);
		delete(smtp);
		return smtp;
	}

	public void delete(Smtp smtp) {
		smtpDao.delete(smtp);
	}

	public void initSmtps() throws IOException {
		String smtpFilelocation = "smtp.properties";
		File file = new File(smtpFilelocation);
		if(!file.exists()) {
			System.out.println("-> Couldn't init SMTPs");
			return;
		}
		Properties smtpProperties = readPropertiesFile(smtpFilelocation);
		Smtp smtp = getSmtpFromProperties(smtpProperties);
		add(smtp);
	}

	private Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}

	private Smtp getSmtpFromProperties(Properties properties) {
		String label = properties.getProperty("label");
		boolean auth = Boolean.parseBoolean(properties.getProperty("auth"));
		int port = Integer.parseInt(properties.getProperty("port"));
		String host = properties.getProperty("host");
		boolean starttlsEnable = Boolean.parseBoolean(properties.getProperty("starttlsEnable"));
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		boolean isSelected = Boolean.parseBoolean(properties.getProperty("selected"));
		Smtp smtp = Smtp.builder()
				.label(label)
				.auth(auth)
				.port(port)
				.host(host)
				.starttlsEnable(starttlsEnable)
				.username(username)
				.password(password)
				.isSelected(isSelected)
				.build();
		return smtp;
	}
}
