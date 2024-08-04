package com.m99.userloginsystem.customexceptionhandler.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@ControllerAdvice
public class JwtExceptionHandlerComponent {

	@ExceptionHandler(value = ExpiredJwtException.class)
	public ResponseEntity<ExpiredJwtException> handleExpiredJwtExceptionr(ExpiredJwtException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception);
	}

	@ExceptionHandler(value = MalformedJwtException.class)
	public ResponseEntity<MalformedJwtException> handleMalformedJwtException(MalformedJwtException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception);
	}
}
