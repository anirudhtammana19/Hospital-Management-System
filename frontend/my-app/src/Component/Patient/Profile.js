import React from "react";
import { Card, Row, Col, Button } from "react-bootstrap";
import "./Profile.css";

const Profile = ({ profile }) => {
  return (
    <Card className="p-4 profile-card">
      <h5 className="mb-3">Profile Info</h5>
      <Row>
        <Col md={6}>
          <p>
            <strong>Name:</strong> {profile.firstName} {profile.lastName}
          </p>
          <p>
            <strong>Date of Birth:</strong> {profile.dateOfBirth}
          </p>
          <p>
            <strong>Phone:</strong> {profile.contactNumber}
          </p>
          <p>
            <strong>Gender:</strong> {profile.gender}
          </p>
          <p style={{marginBottom:"10px"}}>
            <strong>Allergies:</strong> {profile.allergies || "None"}
          </p>
        </Col>
        <Col md={6}>
          <p>
            <strong>Email:</strong> {profile.email}
          </p>
          <p>
            <strong>Emergency Phone:</strong> {profile.emergencyContact}
          </p>
          <p>
            <strong>Blood Group:</strong> {profile.bloodGroup}
          </p>
          <p>
            <strong>Aadhar Card:</strong> {profile.aadharCard}
          </p>
          <p>
            <strong>Address:</strong> {profile.address}
          </p>
        </Col>
      </Row>
      <Button variant="primary" className="mt-3">
        Edit Profile
      </Button>
    </Card>
  );
};

export default Profile;
