package com.meehigh.abcshop.exception;

public class OrderRequestNotFoundException extends RuntimeException {
    public OrderRequestNotFoundException(String message) {
        super(message);
    }
}
