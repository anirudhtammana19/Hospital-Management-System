package com.hexaware.amazecare.Model;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Patient {

    @Id
    @Column(length = 15, nullable = false)
    private String patientId;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column
    @Past(message="Enter correct date of birth")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(nullable = false)
    @Pattern(regexp = "^[0-9]{10}$",message="Enter correct phone number")
    @Size(min=10,max=10)
    private String contactNumber;

    @Column(length = 100, unique = true, nullable = false)
    @Email(message="Enter correct emailid in the format @gmail.com")
    private String email;

    @Column(length = 255)
    private String address;

    @Column
    @Pattern(regexp = "^[0-9]{10}$",message="Enter correct phone number")
    @Size(min=10,max=10)
    private String emergencyContact;

    @Column(columnDefinition = "TEXT")
    private String allergies;

    @Column(length = 12, nullable = false)
    @Pattern(regexp="^[0-9]{12}",message="Enter correct aadhar number")
    @Size(min=12,max=12)
    private String aadharCard;
    
    @Lob
    @Column(columnDefinition="LONGBLOG")
    @NotNull(message="Please insert your aadhar photo")
    private byte[] aadharPhoto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodGroup bloodGroup;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum BloodGroup {
        A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE
    }

	public Patient() {
		super();

	}

	public Patient(String patientId, String firstName, String lastName, Date dateOfBirth, Gender gender,
			String contactNumber, String email, String address, String emergencyContact, String allergies,
			String aadharCard, BloodGroup bloodGroup) {
		super();
		this.patientId = patientId;
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
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
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

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", contactNumber=" + contactNumber
				+ ", email=" + email + ", address=" + address + ", emergencyContact=" + emergencyContact
				+ ", allergies=" + allergies + ", aadharCard=" + aadharCard + ", bloodGroup=" + bloodGroup + "]";
	}
    
    
}
