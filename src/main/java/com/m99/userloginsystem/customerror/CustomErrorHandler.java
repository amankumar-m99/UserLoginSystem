package com.m99.userloginsystem.customerror;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomErrorHandler {

//	@ResponseStatus(HttpStatus.CONFLICT)
//	@ExceptionHandler(BadCredentialsException.class)
	@ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<BadCredentialsException> exceptionHandler(BadCredentialsException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<IllegalArgumentException> exceptionHandler2(IllegalArgumentException exception) {
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(exception);
	}

	@ExceptionHandler(value = UsernameNotFoundException.class)
	public ResponseEntity<UsernameNotFoundException> usernameNotFoundException(UsernameNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
	}

	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<Exception> accessDeniedException(AccessDeniedException exception) {
		return ResponseEntity.status(403).body(new AccessDeniedException(exception.getMessage()));
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Exception> allExceptionHandler(Exception exception) {
		return ResponseEntity.status(500).body(exception);
	}

	@ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<CustomError> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpServletRequest httpServletRequest) {
		CustomError customError = new CustomError(404, "Resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);
    }
}
