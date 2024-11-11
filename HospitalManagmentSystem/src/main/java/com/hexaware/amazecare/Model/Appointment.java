package com.hexaware.amazecare.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "patientId",nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId",nullable = false)
    private Doctor doctor;
    
    @OneToOne(mappedBy = "appointment")
    private MedicalRecord medicalRecord;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    private LocalTime appointmentTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private Status status;

    @Column(length = 255)
    @Size(min=10,message="Please give the reason of consulation briefly")
    private String reason;

    @Column( length = 50)
    @NotNull(message="Enter consultation type")
    private String visitType;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @NotNull(message="Enter consultation type")
    private ConsultationType consultationType;
  
    public enum ConsultationType {
        PHYSICAL, VIRTUAL, HOME_VISIT
    }

    
    public enum Status {
        REQUESTED, RESCHEDULED, SCHEDULED, COMPLETED, CANCELLED
    }

	public Appointment() {
		super();
	}

	public Appointment(int appointmentId, Patient patient, Doctor doctor, LocalDate appointmentDate,
			LocalTime appointmentTime, Status status, String reason, String visitType, ConsultationType consultationType) {
		super();
		this.appointmentId = appointmentId;
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.status = status;
		this.reason = reason;
		this.visitType = visitType;
		this.consultationType = consultationType;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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

	public ConsultationType getConsultationType() {
		return consultationType;
	}

	public void setConsultationType(ConsultationType consultationType) {
		this.consultationType = consultationType;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patient=" + patient + ", doctor=" + doctor
				+ ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + ", status=" + status
				+ ", reason=" + reason + ", visitType=" + visitType + ", consultationType=" + consultationType + "]";
	}
    
}
