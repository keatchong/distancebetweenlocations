package com.keatmin.distancebetweenlocations.exceptionhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PostCodeNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {
		CustomErrorResponse apiErrors = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				ex.getMessage());
		return new ResponseEntity<>(apiErrors, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		Object apiError = (body == null
				? new CustomErrorResponse(LocalDateTime.now(), status.value(), exception.getMessage())
				: body);
		return super.handleExceptionInternal(exception, apiError, headers, status, request);

	}

}