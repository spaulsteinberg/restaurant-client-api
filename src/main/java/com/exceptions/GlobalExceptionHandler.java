package com.exceptions;

import com.exceptions.order.OrderDoesNotExistException;
import com.exceptions.order.OrderNotCreatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(OrderDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleOrderDoesNotExistException(OrderDoesNotExistException exception, WebRequest request){
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(OrderNotCreatedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleOrderNotCreatedException(OrderNotCreatedException exception, WebRequest request){
        return buildErrorResponse(exception, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request){
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Request validation failed.");
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        for (ObjectError error: errors){
            response.addValidationError(((FieldError)error).getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request){
        return buildErrorResponse(exception, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatus status, WebRequest request){
        return buildErrorResponse(exception, status, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus status, WebRequest request){
        return buildErrorResponse(exception, exception.getMessage(), status, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception, String message, HttpStatus status, WebRequest request){
        ErrorResponse response = new ErrorResponse(status.value(), message);
        return ResponseEntity.status(status).body(response);
    }
}
