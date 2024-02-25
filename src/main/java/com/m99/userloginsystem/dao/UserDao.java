package com.m99.userloginsystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.m99.userloginsystem.entity.User;

//public interface UserDao extends CrudRepository<User, Integer>{
public interface UserDao extends JpaRepository<User, Integer>{
	public Optional<User> findByEmail(String email);
}
