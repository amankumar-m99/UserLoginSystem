package com.m99.userloginsystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m99.userloginsystem.entity.Role;

@Repository
//public interface RoleDao extends CrudRepository<Role, Integer>{
public interface RoleDao extends JpaRepository<Role, Integer>{
	public Optional<Role> findByRoleName(String roleName);
}
