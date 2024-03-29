package com.mastery.handler;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mastery.exception.InvalidUserException;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {DataAccessException.class})
  public ResponseEntity<Object> handleDataAccessException(DataAccessException ex, WebRequest request) {
    final ErrorWrapper errorWrapper = new ErrorWrapper(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
    return handleExceptionInternal(ex, errorWrapper, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler(value = {IllegalArgumentException.class})
  public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
    final ErrorWrapper errorWrapper = new ErrorWrapper(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    return handleExceptionInternal(ex, errorWrapper, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = {IllegalStateException.class})
  public ResponseEntity<Object> handleIllegalArgumentException(IllegalStateException ex, WebRequest request) {
    final ErrorWrapper errorWrapper = new ErrorWrapper(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    return handleExceptionInternal(ex, errorWrapper, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = {EmptyResultDataAccessException.class})
  public ResponseEntity<Object> handleIllegalArgumentException(EmptyResultDataAccessException ex, WebRequest request) {
    final ErrorWrapper errorWrapper = new ErrorWrapper(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    return handleExceptionInternal(ex, errorWrapper, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {InvalidUserException.class})
  public ResponseEntity<Object> handleIllegalArgumentException(InvalidUserException ex, WebRequest request) {
    final ErrorWrapper errorWrapper = new ErrorWrapper(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    return handleExceptionInternal(ex, errorWrapper, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
}
