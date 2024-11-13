package com.hexaware.amazecare.DTO;

import java.util.List;

public class MedicalRecordDTO {

	String patientFirstName;
	String doctorFirstName;
    private List<PrescriptionDTO> prescriptions;
    private String symptoms;
    private String physicalExamination;
    private String treatmentPlan;
    private String testsRecommended;
    private String notes;

    public MedicalRecordDTO() {
    	super();
    }

    public MedicalRecordDTO(String patientName, String doctorName, List<PrescriptionDTO> prescriptions, String symptoms,
			String physicalExamination, String treatmentPlan, String testsRecommended, String notes) {
		super();
		this.patientFirstName = patientName;
		this.doctorFirstName = doctorName;
		this.prescriptions = prescriptions;
		this.symptoms = symptoms;
		this.physicalExamination = physicalExamination;
		this.treatmentPlan = treatmentPlan;
		this.testsRecommended = testsRecommended;
		this.notes = notes;
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

	public List<PrescriptionDTO> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<PrescriptionDTO> prescriptions) {
        this.prescriptions = prescriptions;
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
        return "MedicalRecordDTO [prescriptions=" + prescriptions + ", symptoms=" + symptoms + 
               ", physicalExamination=" + physicalExamination + ", treatmentPlan=" + treatmentPlan + 
               ", testsRecommended=" + testsRecommended + ", notes=" + notes + "]";
    }
}
