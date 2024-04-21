package com.m99.userloginsystem.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m99.userloginsystem.entity.user.UserSecurityDetails;

public interface UserSecurityDetailsDao extends JpaRepository<UserSecurityDetails, Integer>{

}
