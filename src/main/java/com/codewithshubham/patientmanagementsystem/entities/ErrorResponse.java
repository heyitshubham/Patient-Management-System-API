package com.codewithshubham.patientmanagementsystem.entities;

public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // getters and setters
}
