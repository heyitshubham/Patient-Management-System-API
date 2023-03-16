package com.codewithshubham.patientmanagementsystem.Exceptions;

public class InvalidRequestException extends RuntimeException {

    private String message;

    public InvalidRequestException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

