package com.m99.userloginsystem.dao.smtp;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m99.userloginsystem.entity.smtp.Smtp;

public interface SmtpDao extends JpaRepository<Smtp, Integer>{
}
