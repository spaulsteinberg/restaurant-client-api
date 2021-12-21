package com.exceptions.order;

public class OrderNotCreatedException extends RuntimeException {
    public OrderNotCreatedException() { super(); }
    public OrderNotCreatedException(String message) { super(message); }
    public OrderNotCreatedException(Throwable cause) { super(cause); }
    public OrderNotCreatedException(String message, Throwable cause) { super(message, cause); }
}
