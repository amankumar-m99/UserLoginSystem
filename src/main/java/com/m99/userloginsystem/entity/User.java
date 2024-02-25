package com.m99.userloginsystem.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String email;
	private String password;

	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
//	@JoinTable(name = "USER_ROLE")
	@JoinTable(name = "USER_ROLE", joinColumns = {@JoinColumn(name="USER_ID")}, inverseJoinColumns = {@JoinColumn(name="ROLE_ID")})
	private Set<Role> role;
}
