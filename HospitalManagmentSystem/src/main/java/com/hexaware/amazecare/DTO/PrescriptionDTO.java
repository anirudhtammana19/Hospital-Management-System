package com.hexaware.amazecare.DTO;

import com.hexaware.amazecare.Model.MedicalRecord;
import com.hexaware.amazecare.Model.Prescription.Frequency;

public class PrescriptionDTO {

    private String medicationName;
    private String dosage;
    private Frequency frequency;
    private int duration;
    private String notes;

    public PrescriptionDTO() {
    	super();
    }

    public PrescriptionDTO( String medicationName, String dosage,
                           Frequency frequency, int duration, String notes) {
        
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.notes = notes;
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
        return "PrescriptionDTO [ medicationName=" + medicationName + ", dosage=" + dosage + ", frequency=" + frequency
                + ", duration=" + duration + ", notes=" + notes + "]";
    }
}
