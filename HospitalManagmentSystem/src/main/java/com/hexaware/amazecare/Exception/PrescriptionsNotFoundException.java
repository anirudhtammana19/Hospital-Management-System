package com.hexaware.amazecare.Exception;

public class PrescriptionsNotFoundException extends Exception {

    public PrescriptionsNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "PrescriptionsNotFoundException: " + super.getMessage();
    }
}
