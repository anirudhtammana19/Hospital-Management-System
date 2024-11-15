package com.hexaware.amazecare.Exception;

public class DuplicatePatientFoundException extends Exception {

    public DuplicatePatientFoundException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "DuplicatePatientFoundException: " + super.getMessage();
    }
}

