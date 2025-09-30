package com.meehigh.abcshop.exception;

public class OrderLineNotFoundException extends RuntimeException {
    public OrderLineNotFoundException(String message) {
        super(message);
    }
}
