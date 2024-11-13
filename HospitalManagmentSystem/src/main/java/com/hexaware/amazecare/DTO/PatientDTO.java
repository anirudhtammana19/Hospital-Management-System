package com.hexaware.amazecare.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class PatientDTO {

    private String firstName;
    private String lastName;
    
    
    private LocalDate dateOfBirth;
    private String gender;
    
    private String contactNumber;
   
    private String email;
    private String address;
    
    private String emergencyContact;
    private String allergies;
    
    
    private String aadharCard;
    
    private String bloodGroup;
    @JsonProperty("password")
    private String password;
    
	public PatientDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientDTO(String firstName, String lastName, LocalDate dateOfBirth, String gender, String contactNumber,
			String email, String address, String emergencyContact, String allergies, String aadharCard,
			String bloodGroup, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.email = email;
		this.address = address;
		this.emergencyContact = emergencyContact;
		this.allergies = allergies;
		this.aadharCard = aadharCard;
		this.bloodGroup = bloodGroup;
		this.password = password;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getAadharCard() {
		return aadharCard;
	}

	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "PatientDTO [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + ", contactNumber=" + contactNumber + ", email=" + email + ", address="
				+ address + ", emergencyContact=" + emergencyContact + ", allergies=" + allergies + ", aadharCard="
				+ aadharCard + ", bloodGroup=" + bloodGroup + ", password=" + password + "]";
	}
    
}

