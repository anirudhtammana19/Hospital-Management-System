package com.hexaware.amazecare.Exception;

public class RecordsNotFoundException extends Exception {

    public RecordsNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "RecordsNotFoundException: " + super.getMessage();
    }
}
