package com.hexaware.amazecare.Exception;

public class DuplicateDoctorFoundException extends Exception {

    public DuplicateDoctorFoundException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "DuplicateDoctorFoundException: " + super.getMessage();
    }
}

