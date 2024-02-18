package com.m99.userloginsystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.m99.userloginsystem.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer>{
}
