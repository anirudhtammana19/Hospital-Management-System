package com.hexaware.amazecare.Model;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int doctorId;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    
    @Column(nullable = false)
    private String profile_image;

    @Column(length = 50, nullable = false)
    private String specialty;

    @Column
    @NotNull
    private int experience;

    @Column(length = 100)
    @NotNull
    private String qualification;

    @Column(length = 100)
    @NotNull
    private String designation;

    @Column(nullable=false)
    @Pattern(regexp = "^[0-9]{10}$",message="Enter correct phone number")
    @Size(min=10,max=10)
    private String contactNumber;

    @Column(length = 100, unique = true, nullable = false)
    @Email(message = "Enter a valid Email ID")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodGroup bloodGroup;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    
    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum BloodGroup {
        A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE
    }

	public Doctor() {

	}

	public Doctor(int doctorId, String firstName, String lastName, String profile, String specialty, int experience,
			String qualification, String designation, String contactNumber, String email, BloodGroup bloodGroup,
			Gender gender) {
		super();
		this.doctorId = doctorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profile_image = profile;
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

	public String getProfile() {
		return profile_image;
	}

	public void setProfile(String profile) {
		this.profile_image = profile;
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
		return "Doctor [doctorId=" + doctorId + ", firstName=" + firstName + ", lastName=" + lastName  + ", specialty=" + specialty + ", experience=" + experience
				+ ", qualification=" + qualification + ", designation=" + designation + ", contactNumber="
				+ contactNumber + ", email=" + email + ", bloodGroup=" + bloodGroup + ", gender=" + gender + "]";
	} 
    
}
