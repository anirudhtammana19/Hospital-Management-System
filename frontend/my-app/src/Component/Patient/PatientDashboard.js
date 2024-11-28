
import React from "react";

import { useParams } from 'react-router-dom';
import { useEffect } from 'react';
import PatientNavBar from '../Navbar/PatientNavbar'
import Header from '../User/Header'
import Profile from './Profile'

const PatientDashboard = (user) => {
    const { patientId } = useParams();

    useEffect(() => {
       ;
    }, [patientId]);
  return (
    <div className="d-flex">
      {/* Sidebar */}
      <div className="col-3">
        <PatientNavBar />
      </div>

      {/* Main Content */}
      <div className="col-9">
        <Header userName={user.name} userType={'Patient'} />
        <div className="container mt-3">
          <Profile profile={user.profile} />
        </div>
      </div>
    </div>
  );
};

export default PatientDashboard;
