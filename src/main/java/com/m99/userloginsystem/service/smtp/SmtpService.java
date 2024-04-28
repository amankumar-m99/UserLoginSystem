package com.m99.userloginsystem.service.smtp;

import java.util.List;

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
}
