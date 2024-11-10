package com.hexaware.amazecare.Model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Doctor {

    @Id
    @Column(length = 15, nullable = false)
    private String doctorId;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profile;

    @Column(length = 50, nullable = false)
    private String specialty;

    @Column
    private int experience;

    @Column(length = 100)
    private String qualification;

    @Column(length = 100)
    private String designation;

    @Column(length = 15)
    private String contactNumber;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodGroup bloodGroup;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum BloodGroup {
        A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE
    }

	public Doctor() {

	}

	public Doctor(String doctorId, String firstName, String lastName, byte[] profile, String specialty, int experience,
			String qualification, String designation, String contactNumber, String email, BloodGroup bloodGroup,
			Gender gender) {
		super();
		this.doctorId = doctorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profile = profile;
		this.specialty = specialty;
		this.experience = experience;
		this.qualification = qualification;
		this.designation = designation;
		this.contactNumber = contactNumber;
		this.email = email;
		this.bloodGroup = bloodGroup;
		this.gender = gender;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
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

	public byte[] getProfile() {
		return profile;
	}

	public void setProfile(byte[] profile) {
		this.profile = profile;
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
		return "Doctor [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName + ", profile="
				+ Arrays.toString(profile) + ", specialty=" + specialty + ", experience=" + experience
				+ ", qualification=" + qualification + ", designation=" + designation + ", contactNumber="
				+ contactNumber + ", email=" + email + ", bloodGroup=" + bloodGroup + ", gender=" + gender + "]";
	} 
    
}
