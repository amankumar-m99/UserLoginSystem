package com.m99.userloginsystem.entity.security;

import java.util.Date;

import com.m99.userloginsystem.model.SecurityCodePurpose;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SecurityCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	private int securityCode;
	private SecurityCodePurpose purpose;
	@Temporal(TemporalType.TIME)
	private Date createdOn;
	private Boolean isUsed;
	private Boolean isExpired;
}
