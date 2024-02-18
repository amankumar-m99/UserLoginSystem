package com.m99.userloginsystem.dao;

import org.springframework.data.repository.CrudRepository;

import com.m99.userloginsystem.entity.User;

public interface UserDao extends CrudRepository<User, Integer>{

}
