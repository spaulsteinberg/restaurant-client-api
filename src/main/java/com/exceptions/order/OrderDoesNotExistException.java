package com.exceptions.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderDoesNotExistException extends RuntimeException {
    public OrderDoesNotExistException() { super(); }
    public OrderDoesNotExistException(String message) { super(message); }
    public OrderDoesNotExistException(Throwable cause) { super(cause); }
    public OrderDoesNotExistException(String message, Throwable cause) { super(message, cause); }
}
