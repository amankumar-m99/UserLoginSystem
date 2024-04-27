package com.m99.userloginsystem.dao.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m99.userloginsystem.entity.security.EmailSecurityCode;

public interface EmailSecurityCodeDao extends JpaRepository<EmailSecurityCode, Integer> {
	public Optional<EmailSecurityCode> findByEmail(String email);
}
