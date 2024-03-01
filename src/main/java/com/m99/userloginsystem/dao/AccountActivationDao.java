package com.m99.userloginsystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m99.userloginsystem.entity.AccountActivation;

public interface AccountActivationDao extends JpaRepository<AccountActivation, Integer> {
	public Optional<AccountActivation> findByActivationKey(String key);
}
