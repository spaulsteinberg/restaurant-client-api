package com.exceptions.order;

public class OrderDoesNotExistException extends RuntimeException {
    public OrderDoesNotExistException() { super(); }
    public OrderDoesNotExistException(String message) { super(message); }
    public OrderDoesNotExistException(Throwable cause) { super(cause); }
    public OrderDoesNotExistException(String message, Throwable cause) { super(message, cause); }
}
