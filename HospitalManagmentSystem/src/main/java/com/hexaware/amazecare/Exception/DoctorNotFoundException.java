package com.hexaware.amazecare.Exception;

public class DoctorNotFoundException extends Exception {

    public DoctorNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "DoctorNotFoundException: " + super.getMessage();
    }
}

