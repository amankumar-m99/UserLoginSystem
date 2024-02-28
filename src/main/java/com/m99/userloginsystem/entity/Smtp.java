package com.m99.userloginsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Smtp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int port;
	private String host;
	private String username;
	private String password;
	private Boolean auth;
	private Boolean starttlsEnable;
}
