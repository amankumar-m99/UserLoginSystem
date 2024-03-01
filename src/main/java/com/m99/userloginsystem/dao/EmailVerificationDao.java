package com.m99.userloginsystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m99.userloginsystem.entity.EmailVerification;

public interface EmailVerificationDao extends JpaRepository<EmailVerification, Integer> {
	public Optional<EmailVerification> findByVerificationCode(String code);
}
