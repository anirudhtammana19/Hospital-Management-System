import React, { useState } from 'react';
import { Form, Button, Input, Dropdown, Modal } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';

const SignupForm = ({ open, handleClose }) => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    dateOfBirth: '',
    gender: '',
    contactNumber: '',
    email: '',
    address: '',
    emergencyContact: '',
    allergies: '',
    aadharCard: '',
    bloodGroup: '',
    password: '',
  });

  const handleChange = (e, { name, value }) => {
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
    handleClose(); // Close the modal after submitting the form
  };

  const handleCancel = () => {
    setFormData({
      firstName: '',
      lastName: '',
      dateOfBirth: '',
      gender: '',
      contactNumber: '',
      email: '',
      address: '',
      emergencyContact: '',
      allergies: '',
      aadharCard: '',
      bloodGroup: '',
      password: '',
    });
    handleClose(); // Close the modal when cancel is clicked
  };

  const genderOptions = [
    { key: 'male', text: 'Male', value: 'MALE' },
    { key: 'female', text: 'Female', value: 'FEMALE' },
    { key: 'other', text: 'Other', value: 'OTHER' },
  ];

  const bloodGroupOptions = [
    { key: 'a_positive', text: 'A Positive', value: 'A_POSITIVE' },
    { key: 'b_positive', text: 'B Positive', value: 'B_POSITIVE' },
    { key: 'o_positive', text: 'O Positive', value: 'O_POSITIVE' },
    { key: 'ab_positive', text: 'AB Positive', value: 'AB_POSITIVE' },
    { key: 'a_negative', text: 'A Negative', value: 'A_NEGATIVE' },
    { key: 'b_negative', text: 'B Negative', value: 'B_NEGATIVE' },
    { key: 'o_negative', text: 'O Negative', value: 'O_NEGATIVE' },
    { key: 'ab_negative', text: 'AB Negative', value: 'AB_NEGATIVE' },
  ];

  return (
    <Modal open={open} onClose={handleClose} size="small">
      <Modal.Header>Signup Form</Modal.Header>
      <Modal.Content>
        <Form onSubmit={handleSubmit}>
          <Form.Group widths="equal">
            <Form.Field
              control={Input}
              label="First Name"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
              required
            />
            <Form.Field
              control={Input}
              label="Last Name"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
              required
            />
          </Form.Group>

          <Form.Field
            control={Input}
            label="Date of Birth"
            name="dateOfBirth"
            type="date"
            value={formData.dateOfBirth}
            onChange={handleChange}
            required
          />

          <Form.Field
            control={Dropdown}
            label="Gender"
            name="gender"
            value={formData.gender}
            onChange={handleChange}
            options={genderOptions}
            selection
            required
          />

          <Form.Field
            control={Input}
            label="Contact Number"
            name="contactNumber"
            type="tel"
            value={formData.contactNumber}
            onChange={handleChange}
            required
          />

          <Form.Field
            control={Input}
            label="Email"
            name="email"
            type="email"
            value={formData.email}
            onChange={handleChange}
            required
          />

          <Form.Field
            control={Input}
            label="Address"
            name="address"
            value={formData.address}
            onChange={handleChange}
            required
          />

          <Form.Field
            control={Input}
            label="Emergency Contact"
            name="emergencyContact"
            type="tel"
            value={formData.emergencyContact}
            onChange={handleChange}
            required
          />

          <Form.Field
            control={Input}
            label="Allergies"
            name="allergies"
            value={formData.allergies}
            onChange={handleChange}
          />

          <Form.Field
            control={Input}
            label="Aadhar Card"
            name="aadharCard"
            value={formData.aadharCard}
            onChange={handleChange}
            required
          />

          <Form.Field
            control={Dropdown}
            label="Blood Group"
            name="bloodGroup"
            value={formData.bloodGroup}
            onChange={handleChange}
            options={bloodGroupOptions}
            selection
            required
          />

          <Form.Field
            control={Input}
            label="Password"
            name="password"
            type="password"
            value={formData.password}
            onChange={handleChange}
            required
          />

          <Button type="submit" primary>
            Submit
          </Button>
          <Button type="button" onClick={handleCancel}>
            Cancel
          </Button>
        </Form>
      </Modal.Content>
    </Modal>
  );
};

export default SignupForm;