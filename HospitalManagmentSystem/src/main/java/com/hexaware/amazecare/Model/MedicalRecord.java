package com.hexaware.amazecare.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int recordId;

    @ManyToOne
    @JoinColumn(name = "patientId",nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctorId",nullable = false)
    private Doctor doctor;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "appointmentId",nullable = false)
    private Appointment appointment;
    
    @OneToMany(mappedBy = "medicalRecord",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Prescription> prescriptions;

    @Column(columnDefinition = "TEXT")
    private String symptoms;

    @Column( columnDefinition = "TEXT")
    private String physicalExamination;

    @Column(columnDefinition = "TEXT")
    private String treatmentPlan;

    @Column(columnDefinition = "TEXT")
    private String testsRecommended;

    @Column(columnDefinition = "TEXT")
    private String notes;

	public MedicalRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicalRecord(int recordId, Patient patient, Doctor doctor, Appointment appointment,
			List<Prescription> prescriptions, String symptoms, String physicalExamination, String treatmentPlan,
			String testsRecommended, String notes) {
		super();
		this.recordId = recordId;
		this.patient = patient;
		this.doctor = doctor;
		this.appointment = appointment;
		this.prescriptions = prescriptions;
		this.symptoms = symptoms;
		this.physicalExamination = physicalExamination;
		this.treatmentPlan = treatmentPlan;
		this.testsRecommended = testsRecommended;
		this.notes = notes;
	}



	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
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

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getPhysicalExamination() {
		return physicalExamination;
	}

	public void setPhysicalExamination(String physicalExamination) {
		this.physicalExamination = physicalExamination;
	}

	public String getTreatmentPlan() {
		return treatmentPlan;
	}

	public void setTreatmentPlan(String treatmentPlan) {
		this.treatmentPlan = treatmentPlan;
	}

	public String getTestsRecommended() {
		return testsRecommended;
	}

	public void setTestsRecommended(String testsRecommended) {
		this.testsRecommended = testsRecommended;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	@Override
	public String toString() {
		return "MedicalRecord [recordId=" + recordId + ", patient=" + patient + ", doctor=" + doctor + ", appointment="
				+ appointment.getAppointmentId() + ", symptoms=" + symptoms + ", physicalExamination=" + physicalExamination
				+ ", treatmentPlan=" + treatmentPlan + ", testsRecommended=" + testsRecommended + ", notes=" + notes
				+ "]";
	}

}
