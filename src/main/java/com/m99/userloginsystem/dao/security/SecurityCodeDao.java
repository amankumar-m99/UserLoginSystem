package com.m99.userloginsystem.dao.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.m99.userloginsystem.entity.security.SecurityCode;

public interface SecurityCodeDao extends JpaRepository<SecurityCode, Integer> {
	public Optional<SecurityCode> findByEmail(String email);

	@Query("SELECT s FROM SecurityCode s WHERE s.email=?1 and s.securityCode=?2")
	public Optional<SecurityCode> findByEmailAndSecurityCode(String email, int securityCode);
}
