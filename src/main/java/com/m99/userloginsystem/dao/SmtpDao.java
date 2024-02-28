package com.m99.userloginsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m99.userloginsystem.entity.Smtp;

public interface SmtpDao extends JpaRepository<Smtp, Integer>{
}
