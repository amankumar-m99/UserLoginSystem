package com.m99.userloginsystem.dao.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m99.userloginsystem.entity.security.SecurityCode;

public interface SecurityCodeDao extends JpaRepository<SecurityCode, Integer> {
	public Optional<SecurityCode> findByEmail(String email);
}
