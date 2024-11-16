package com.hexaware.amazecare.Exception;

public class AppointmentCancelledException extends Exception{
	
	public AppointmentCancelledException(String msg) {
		super(msg);
	}
	
	public String getMessage() {
		return "AppointmentCancelledException"+super.getMessage();
	}
}
