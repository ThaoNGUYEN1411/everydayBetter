package co.simplon.everydaybetterbusiness.controllers;

import co.simplon.everydaybetterbusiness.dtos.ApiErrorResponse;
import co.simplon.everydaybetterbusiness.dtos.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
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
import java.util.List;
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
        //System.out.println(exception.getBindingResult().getAllErrors()); this line to see how to error display
        List<ApiErrorResponse.ErrorDetail> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    ApiErrorResponse.ErrorDetail detail = new ApiErrorResponse.ErrorDetail();
                    detail.setField(fieldError.getField()); //nickname, email
                    detail.setCode(fieldError.getCode()); // "NotBlank", "Size"
                    detail.setMessage(fieldError.getDefaultMessage());
                    return detail;
                })
                .collect(Collectors.toList());

        // global errors
        List<ApiErrorResponse.ErrorDetail> globalErrors = exception.getBindingResult().getGlobalErrors().stream()
                .map(globalError -> {
                    ApiErrorResponse.ErrorDetail detail = new ApiErrorResponse.ErrorDetail();
                    detail.setCode(globalError.getCode());
                    detail.setMessage(globalError.getDefaultMessage());
                    return detail;
                })
                .toList();

        errors.addAll(globalErrors);

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setErrors(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
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

//    @ExceptionHandler(ResourceNotFoundException.class)
//    protected ResponseEntity<ErrorDto> handleResourceNotFoundException(final ResourceNotFoundException exception) {
//        final var message = exception.getMessage();
//        final var errorDetail = new ErrorDto(message);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
//    }
}
