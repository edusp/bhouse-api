package com.br.bhouse.api.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, addMessagesError(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	private List<Error> addMessagesError(BindingResult bindingResult) {
		ArrayList<Error> errorList = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String toUserMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			errorList.add(new Error(toUserMessage, fieldError.toString()));
		}
		return errorList;
	}
	
	

	
	static class Error {

		private String toUserMessage;
		private String toDevMessage;

		public Error(String toUserMessage, String toDevMessage) {
			this.toUserMessage = toUserMessage;
			this.toDevMessage = toDevMessage;
		}

		public String getToUserMessage() {
			return toUserMessage;
		}

		public String getToDevMessage() {
			return toDevMessage;
		}

	}

}
