package com.hexaware.amazecare.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DoctorDTO {
    
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String profile_image;

    @NotNull
    private String specialty;

    @NotNull
    private int experience;

    @NotNull
    private String qualification;

    @NotNull
    private String designation;

    @Pattern(regexp = "^[0-9]{10}$", message = "Enter correct phone number")
    @Size(min = 10, max = 10)
    private String contactNumber;

    @Email(message = "Enter a valid Email ID")
    @NotNull
    private String email;

    @NotNull
    private BloodGroup bloodGroup;

    @NotNull
    private Gender gender;
    
    @Pattern(regexp="^(?=.*[A-Z])(?=.*[!@#$%^&*()<>,.?\":{}|]).{7,}$",
    	    message="Password must be at least 7 characters long, contain at least one uppercase letter, and one special character.")
    private String password;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum BloodGroup {
        A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE
    }

    public DoctorDTO() {
        // Default constructor
    	super();
    }

	public DoctorDTO(@NotNull String firstName, @NotNull String lastName, String profile_image,
			@NotNull String specialty, @NotNull int experience, @NotNull String qualification,
			@NotNull String designation,
			@Pattern(regexp = "^[0-9]{10}$", message = "Enter correct phone number") @Size(min = 10, max = 10) String contactNumber,
			@Email(message = "Enter a valid Email ID") @NotNull String email, @NotNull BloodGroup bloodGroup,
			@NotNull Gender gender,
			@Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()<>,.?\":{}|]).{7,}$", message = "Password must be at least 7 characters long, contain at least one uppercase letter, and one special character.") String password) {
		super();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DoctorDTO [firstName=" + firstName + ", lastName=" + lastName + ", profile_image=" + profile_image
				+ ", specialty=" + specialty + ", experience=" + experience + ", qualification=" + qualification
				+ ", designation=" + designation + ", contactNumber=" + contactNumber + ", email=" + email
				+ ", bloodGroup=" + bloodGroup + ", gender=" + gender + ", password=" + password + "]";
	}

}
