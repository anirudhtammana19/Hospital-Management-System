package com.hexaware.amazecare.DTO;

import com.hexaware.amazecare.Model.Doctor.BloodGroup;
import com.hexaware.amazecare.Model.Doctor.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DoctorDetailsDTO {
    
	int doctorId;
    private String firstName;
    private String lastName;

    private String profile_image;

    private String specialty;

    private int experience;

    private String qualification;

    private String designation;

    private String contactNumber;
    private String email;

    private BloodGroup bloodGroup;

    private Gender gender;
    


    public DoctorDetailsDTO() {
        // Default constructor
    	super();
    }    
    
    public DoctorDetailsDTO(int doctorId, String firstName, String lastName, String profile_image, String specialty,
			int experience, String qualification, String designation, String contactNumber, String email,
			BloodGroup bloodGroup, Gender gender) {
		super();
		this.doctorId = doctorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profile_image = profile_image;
		this.specialty = specialty;
		this.experience = experience;
		this.qualification = qualification;
		this.designation = designation;
		this.contactNumber = contactNumber;
		this.email = email;
		this.bloodGroup = bloodGroup;
		this.gender = gender;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}



	@Override
	public String toString() {
		return "DoctorDTO [firstName=" + firstName + ", lastName=" + lastName + ", profile_image=" + profile_image
				+ ", specialty=" + specialty + ", experience=" + experience + ", qualification=" + qualification
				+ ", designation=" + designation + ", contactNumber=" + contactNumber + ", email=" + email
				+ ", bloodGroup=" + bloodGroup + ", gender=" + gender + "]";
	}

}
