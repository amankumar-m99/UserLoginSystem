package com.m99.userloginsystem.service.email;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m99.userloginsystem.customexception.smtp.NoSuchSmtpException;
import com.m99.userloginsystem.dao.SmtpDao;
import com.m99.userloginsystem.entity.Smtp;

@Service
public class SmtpService {

	@Autowired
	private SmtpDao smtpDao;

	public Smtp add(Smtp smtp) {
		return smtpDao.save(smtp);
	}

	public Smtp edit(Smtp smtp) {
		return smtpDao.save(smtp);
	}

	public Smtp getById(int id) throws NoSuchSmtpException {
		return smtpDao.findById(id).orElseThrow(()->new NoSuchSmtpException("No smtp exists with id "+ id));
	}

	public List<Smtp> getAllSmtp(){
		return smtpDao.findAll();
	}

	public Smtp deleteById(int id) throws NoSuchSmtpException {
		Smtp smtp = getById(id);
		delete(smtp);
		return smtp;
	}

	public void delete(Smtp smtp) {
		smtpDao.delete(smtp);
	}
}
