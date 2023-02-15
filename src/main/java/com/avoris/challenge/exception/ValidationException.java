package com.avoris.challenge.exception;

public class ValidationException extends RuntimeException {

    private final String fieldName;
    public ValidationException(String fiendName, String message) {
        super(message);
        this.fieldName = fiendName;
    }

    public String getFieldName() {
        return fieldName;
    }

}
