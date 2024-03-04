package com.m99.userloginsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m99.userloginsystem.entity.EmailVerification;

public interface EmailSecurityCodeDao extends JpaRepository<EmailVerification, Integer> {

}
