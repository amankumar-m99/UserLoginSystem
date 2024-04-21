package com.m99.userloginsystem.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m99.userloginsystem.entity.user.User;

//public interface UserDao extends CrudRepository<User, Integer>{
public interface UserDao extends JpaRepository<User, Long>{
	public Optional<User> findByEmail(String email);
	public Optional<User> findByUsername(String username);
}
