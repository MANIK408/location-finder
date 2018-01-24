package com.project.locate.common.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.locate.common.validation.ValidationUtil;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<EmptyDataResponse> resourceNotFound(ResourceNotFoundException ex) {
		EmptyDataResponse response = createErrorResponse("404", ex.getMessage(), null);
		return new ResponseEntity<EmptyDataResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<EmptyDataResponse> invalidArgument(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		EmptyDataResponse response = createErrorResponseEntity("400", ex.getMessage(),
				ValidationUtil.fromBindingErrors(result));
		return new ResponseEntity<EmptyDataResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BusinessValidationException.class)
	public ResponseEntity<EmptyDataResponse> businessValidationException(BusinessValidationException ex) {
		EmptyDataResponse response = createErrorResponse("400", ex.getMessage(), null);
		return new ResponseEntity<EmptyDataResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<EmptyDataResponse> unauthorized(UnauthorizedException ex) {
		EmptyDataResponse response = createErrorResponse("403", ex.getMessage(), null);
		return new ResponseEntity<EmptyDataResponse>(response, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<EmptyDataResponse> serverError(RuntimeException ex) {
		EmptyDataResponse response = createErrorResponse("500", ex.getMessage(), null);
		return new ResponseEntity<EmptyDataResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UnauthenticatedException.class)
	public ResponseEntity<EmptyDataResponse> unauthenticated(UnauthenticatedException ex) {
		EmptyDataResponse response = createErrorResponse("401", ex.getMessage(), null);
		return new ResponseEntity<EmptyDataResponse>(response, HttpStatus.UNAUTHORIZED);
	}

	private EmptyDataResponse createErrorResponse(String statusCode, String errCode, String errorDetail) {
		Notification notification = buildNotification(statusCode, errCode,
				StringUtils.isBlank(errorDetail) ? StringUtils.EMPTY : errorDetail);
		List<Notification> notifications = new ArrayList<Notification>();
		notifications.add(notification);
		EmptyDataResponse emptyDataResponse = new EmptyDataResponse();
		emptyDataResponse.setNotifications(notifications);
		return emptyDataResponse;

	}

	private EmptyDataResponse createErrorResponseEntity(String statusCode, String errCode, List<String> errorDetails) {
		EmptyDataResponse emptyDataResponse = new EmptyDataResponse();
		Notification notification = null;
		List<Notification> notifications = new ArrayList<Notification>();
		for (String errorDetail : errorDetails) {
			notification = buildNotification(statusCode, errCode, errorDetail);
			notifications.add(notification);
		}
		emptyDataResponse.setNotifications(notifications);
		return emptyDataResponse;
	}

	private Notification buildNotification(String statusCode, String errCode, String errorDetail) {
		Notification notification = new Notification();
		notification.setCode("E" + errCode + errorDetail);
		DateTime dateTime = new DateTime();
		notification.setTimestamp(dateTime.toString());
		notification.setUuid(UUID.randomUUID().toString());
		return notification;
	}
}
