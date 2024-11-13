package com.hexaware.amazecare.Exception;

public class AppointmentNotFoundException extends Exception {

    public AppointmentNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "AppointmentNotFoundException: " + super.getMessage();
    }
}
