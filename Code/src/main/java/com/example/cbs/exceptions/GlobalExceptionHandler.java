package com.example.cbs.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cbs.exceptions.pojo.ExceptionStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {
	

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleBeanValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);

		});
		errors.put("errorCode", String.valueOf(HttpStatus.BAD_REQUEST.value()));
		errors.put("errorMessage", "Validation failed!");
		return errors;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DataNotFoundException.class)
	public ExceptionStatus userNotFoundException(Exception e) {
		return new ExceptionStatus(new Date(), 404, "DataNotFoundException", e.getMessage());
	}

}
