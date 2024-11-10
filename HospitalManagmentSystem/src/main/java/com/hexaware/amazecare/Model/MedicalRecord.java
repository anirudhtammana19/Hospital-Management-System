package com.hexaware.amazecare.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class MedicalRecord {

    @Id
    @Column( length = 15)
    private String recordId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(nullable = false)
    private Appointment appointment;

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

	public MedicalRecord(String recordId, Patient patient, Doctor doctor, Appointment appointment, String symptoms,
			String physicalExamination, String treatmentPlan, String testsRecommended, String notes) {
		super();
		this.recordId = recordId;
		this.patient = patient;
		this.doctor = doctor;
		this.appointment = appointment;
		this.symptoms = symptoms;
		this.physicalExamination = physicalExamination;
		this.treatmentPlan = treatmentPlan;
		this.testsRecommended = testsRecommended;
		this.notes = notes;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
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

	@Override
	public String toString() {
		return "MedicalRecord [recordId=" + recordId + ", patient=" + patient + ", doctor=" + doctor + ", appointment="
				+ appointment + ", symptoms=" + symptoms + ", physicalExamination=" + physicalExamination
				+ ", treatmentPlan=" + treatmentPlan + ", testsRecommended=" + testsRecommended + ", notes=" + notes
				+ "]";
	}

}
