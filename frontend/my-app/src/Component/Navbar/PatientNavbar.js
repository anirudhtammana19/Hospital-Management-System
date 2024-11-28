import React from "react";
import { Nav } from "react-bootstrap";

const PatientNavBar = () => {
  return (
    <div className="bg-primary text-white vh-100 p-3">
      <h5 className="mb-4">Welcome, Patient</h5>
      <Nav defaultActiveKey="/dashboard" className="flex-column">
        <Nav.Link href="/dashboard" className="text-white">
          Dashboard
        </Nav.Link>
        <Nav.Link href="/doctors" className="text-white">
          Doctors
        </Nav.Link>
        <Nav.Link href="/appointments" className="text-white">
          Appointments
        </Nav.Link>
        <Nav.Link href="/medical-record" className="text-white">
          Medical Record
        </Nav.Link>
      </Nav>
    </div>
  );
};

export default PatientNavBar;
