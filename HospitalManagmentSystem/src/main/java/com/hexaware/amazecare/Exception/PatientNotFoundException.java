package com.hexaware.amazecare.Exception;

public class PatientNotFoundException extends Exception {

    public PatientNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "PatientNotFoundException: " + super.getMessage();
    }
}
