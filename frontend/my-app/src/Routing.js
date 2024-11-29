import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './Component/User/Login';
import Register from './Component/User/Register';
import PatientDashboard from './Component/Patient/PatientDashboard';
import PatientDoctors from './Component/Patient/ViewAllDoctors/ViewAllDoctors';
import PatientAppointment from './Component/Patient/ViewAppoinments/ViewAppointments'
import PatientMedicalRecords from './Component/Patient/ViewMedicalRecords/ViewMedicalRecords';

const routing = () => {
  return (
    <Routes>
      
      <Route path="/login" element={<Login />} />
      <Route path="/Signup" element={<Register/>}/>
      
      <Route path="/patient/dashboard" element={<PatientDashboard/>} />
      <Route path="/patient/doctors" element={<PatientDoctors/>} />
      <Route path="/patient/appointments" element={<PatientAppointment />} />
      <Route path="/patient/records" element={<PatientMedicalRecords/>} />

    </Routes>
  );
};

export default routing;
