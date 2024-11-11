package com.hexaware.amazecare.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {

    private int appointmentId;
    private int patientId;
    private int doctorId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;
    private String reason;
    private String visitType;
    private String consultationType;

    public AppointmentDTO() {
    	super();
    }
    	
        
    // Constructor
    public AppointmentDTO(int appointmentId, int patientId, int doctorId, LocalDate appointmentDate, 
                          LocalTime appointmentTime, String status, String reason, 
                          String visitType, String consultationType) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.reason = reason;
        this.visitType = visitType;
        this.consultationType = consultationType;
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }

    // toString method
    @Override
    public String toString() {
        return "AppointmentDTO [appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId
                + ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + ", status=" + status
                + ", reason=" + reason + ", visitType=" + visitType + ", consultationType=" + consultationType + "]";
    }
}
