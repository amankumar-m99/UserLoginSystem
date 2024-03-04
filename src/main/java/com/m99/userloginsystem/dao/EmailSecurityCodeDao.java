package com.m99.userloginsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m99.userloginsystem.entity.EmailSecurityCode;

public interface EmailSecurityCodeDao extends JpaRepository<EmailSecurityCode, Integer> {

}
