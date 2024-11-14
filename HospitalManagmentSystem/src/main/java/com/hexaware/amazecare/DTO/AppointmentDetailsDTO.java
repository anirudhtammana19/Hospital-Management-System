package com.hexaware.amazecare.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.hexaware.amazecare.Model.Appointment.ConsultationType;
import com.hexaware.amazecare.Model.Appointment.Status;

public class AppointmentDetailsDTO {

	int appointmentId;
	int patientId;
	String patientFirstName;
	int doctorId;
	String doctorFirstName;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Status status;
    private String reason;
    private String visitType;
    private ConsultationType consultationType;

    public AppointmentDetailsDTO() {
    	super();
    }

	public AppointmentDetailsDTO(int appointmentId, int patientId, String patientFirstName, int doctorId,
			String doctorFirstName, LocalDate appointmentDate, LocalTime appointmentTime, Status status, String reason,
			String visitType, ConsultationType consultationType) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.patientFirstName = patientFirstName;
		this.doctorId = doctorId;
		this.doctorFirstName = doctorFirstName;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.status = status;
		this.reason = reason;
		this.visitType = visitType;
		this.consultationType = consultationType;
	}

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

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getDoctorFirstName() {
		return doctorFirstName;
	}

	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
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

	

    // toString method
    @Override
    public String toString() {
        return "AppointmentDTO [ appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + ", status=" + status
                + ", reason=" + reason + ", visitType=" + visitType + ", consultationType=" + consultationType + "]";
    }
}
