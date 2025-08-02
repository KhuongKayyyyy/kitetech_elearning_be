package com.example.kitetech_elearning_be.exception;

public class InvalidCredentialException extends RuntimeException {
    public  InvalidCredentialException(String message) {
        super(message);
    }
}