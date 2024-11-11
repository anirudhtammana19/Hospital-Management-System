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

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int prescriptionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private MedicalRecord medicalRecord;

    @Column(length = 100, nullable = false)
    private String medicationName;

    @Column(length = 50, nullable = false)
    private String dosage;

    @Enumerated(EnumType.STRING)
    @Column(length = 2, nullable = false)
    private Frequency frequency;

    @Column(nullable = false)
    private int duration; 

    @Column(columnDefinition = "TEXT")
    private String notes;

    public enum Frequency {
        AF, // After Food
        BF // Before Food
    }

	public Prescription() {
		super();
	}

	public Prescription(int prescriptionId, MedicalRecord medicalRecord, String medicationName, String dosage,
			Frequency frequency, int duration, String notes) {
		super();
		this.prescriptionId = prescriptionId;
		this.medicalRecord = medicalRecord;
		this.medicationName = medicationName;
		this.dosage = dosage;
		this.frequency = frequency;
		this.duration = duration;
		this.notes = notes;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", medicalRecord=" + medicalRecord
				+ ", medicationName=" + medicationName + ", dosage=" + dosage + ", frequency=" + frequency
				+ ", duration=" + duration + ", notes=" + notes + "]";
	}

}
