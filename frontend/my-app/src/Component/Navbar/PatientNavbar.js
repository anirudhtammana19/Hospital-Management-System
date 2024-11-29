import React from "react";
import { Nav } from "react-bootstrap";
import "./PatientNavbar.css";
import {Link} from 'react-router-dom'

const PatientNavBar = () => {
  return (
    <div className="sidebar bg-primary text-white vh-100 p-3">
      <h2 className="mb-4">Welcome, Patient</h2>
      <Nav defaultActiveKey="/patient/dashboard" className="flex-column">
        <Nav.Link className="text-white">
          <i className="bi bi-speedometer2 me-2"></i>Dashboard
        </Nav.Link>
        <Nav.Link  className="text-white">
          <i className="bi bi-person-fill me-2"></i>Doctors
        </Nav.Link>
        <Nav.Link   className="text-white">
          <i className="bi bi-calendar-check-fill me-2"></i>Appointments
        </Nav.Link>
        <Nav.Link  className="text-white">
          <i className="bi bi-file-earmark-text-fill me-2"></i>Medical Record
        </Nav.Link>
      </Nav>
    </div>
  );
};

export default PatientNavBar;
