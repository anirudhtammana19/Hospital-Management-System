package com.hexaware.amazecare.DTO;

import java.util.List;

public class MedicalRecordDTO {

    private String symptoms;
    private String physicalExamination;
    private String treatmentPlan;
    private String testsRecommended;
    private String notes;

    public MedicalRecordDTO() {
    	super();
    }
    
	public MedicalRecordDTO( String symptoms, String physicalExamination, String treatmentPlan,
			String testsRecommended, String notes) {
		super();
		this.symptoms = symptoms;
		this.physicalExamination = physicalExamination;
		this.treatmentPlan = treatmentPlan;
		this.testsRecommended = testsRecommended;
		this.notes = notes;
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

    // toString method
    @Override
    public String toString() {
        return "MedicalRecordDTO [ symptoms=" + symptoms + 
               ", physicalExamination=" + physicalExamination + ", treatmentPlan=" + treatmentPlan + 
               ", testsRecommended=" + testsRecommended + ", notes=" + notes + "]";
    }
}
