package com.m99.userloginsystem.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.m99.userloginsystem.customexception.email.EmailAlreadyExistsException;
import com.m99.userloginsystem.customexception.email.EmailNotFoundException;
import com.m99.userloginsystem.customexception.email.EmailNotVerifiedException;
import com.m99.userloginsystem.customexception.smtp.NoSuchSmtpException;
import com.m99.userloginsystem.customexception.user.UserNameNotAvailableException;
import com.m99.userloginsystem.utils.CustomError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandlerComponent {

	@ExceptionHandler(value = NoSuchSmtpException.class)
	public ResponseEntity<NoSuchSmtpException> handleNoSuchSmtpException(NoSuchSmtpException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
	}

	@ExceptionHandler(value = EmailNotVerifiedException.class)
	public ResponseEntity<EmailNotVerifiedException> handleEmailNotVerifiedException(EmailNotVerifiedException exception) {
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(exception);
	}

	@ExceptionHandler(value = UserNameNotAvailableException.class)
	public ResponseEntity<UserNameNotAvailableException> handleUserNameNotAvailableException(UserNameNotAvailableException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
	}

	@ExceptionHandler(value = EmailAlreadyExistsException.class)
	public ResponseEntity<EmailAlreadyExistsException> handleUserNameNotAvailableException(EmailAlreadyExistsException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
	}

	@ExceptionHandler(value = EmailNotFoundException.class)
	public ResponseEntity<EmailNotFoundException> handleEmailNotFoundException(EmailNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
	}

	@ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<CustomError> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpServletRequest httpServletRequest) {
		CustomError customError = new CustomError(404, "Resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);
    }
}
