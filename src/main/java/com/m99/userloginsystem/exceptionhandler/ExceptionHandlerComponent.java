package com.m99.userloginsystem.exceptionhandler;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerComponent {

//	@ResponseStatus(HttpStatus.CONFLICT)
//	@ExceptionHandler(BadCredentialsException.class)

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<IllegalArgumentException> handleIllegalArgumentException(IllegalArgumentException exception) {
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(exception);
	}

	@ExceptionHandler(value = UsernameNotFoundException.class)
	public ResponseEntity<UsernameNotFoundException> handleUsernameNotFoundException(UsernameNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
	}

	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<NoSuchElementException> handleNoSuchElementException(NoSuchElementException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
	}
	
	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<Exception> handleAccessDeniedException(AccessDeniedException exception) {
		return ResponseEntity.status(403).body(new AccessDeniedException(exception.getMessage()));
	}

	@ExceptionHandler(value = DisabledException.class)
	public ResponseEntity<DisabledException> handleDisabledException(DisabledException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception);
	}
	
	@ExceptionHandler(value = LockedException.class)
	public ResponseEntity<LockedException> handleLockedException(LockedException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception);
	}
	
	@ExceptionHandler(value = BadCredentialsException.class)
	public ResponseEntity<BadCredentialsException> handleBadCredentialException(BadCredentialsException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception);
	}

	@ExceptionHandler(value = AuthenticationException.class)
	public ResponseEntity<AuthenticationException> handleAuthenticationException(AuthenticationException exception) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Exception> handleException(Exception exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
	}
}
