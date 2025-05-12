package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {

        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<Object> handleDataAccessException(DataAccessException ex, WebRequest request) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        final String message = exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        final var errorDetails = new ErrorDto(message);
        logger.error(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

//toto: explain detail
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorDto> handleConstraintViolationException(final ConstraintViolationException exception){
    final Map<String, String> errors = new HashMap<>();
    exception.getConstraintViolations().forEach(error -> {
        final String fieldName = error.getPropertyPath().toString();
        final String errorMessage = error.getMessage();
        errors.put(fieldName, errorMessage);
    });
    final var errorDetails = new ErrorDto(StringUtils.join(errors));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

//    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
//    protected ResponseEntity<ErrorDto> handleResourceNotFound(ResourceNotFoundException ex) {
//        return ResponseEntity<>("Resource not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
}

