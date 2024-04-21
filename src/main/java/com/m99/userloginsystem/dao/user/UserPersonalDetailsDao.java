package com.m99.userloginsystem.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m99.userloginsystem.entity.user.UserPersonalDetails;

public interface UserPersonalDetailsDao extends JpaRepository<UserPersonalDetails, Integer>{

}
