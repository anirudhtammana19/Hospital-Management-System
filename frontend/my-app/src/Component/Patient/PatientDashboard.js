
import React, { useEffect, useState } from "react";
import PatientNavBar from '../Navbar/PatientNavbar'
import Header from '../User/Header'
import Profile from './Profile'
import './PatientDashboard.css';
import axios from 'axios'

const PatientDashboard = () => {

  const [user, setuser] = useState({})
  useEffect(() => {
    let token=localStorage.getItem('token')
    axios.get("http://localhost:8080/api/patient/viewProfile",{
      headers:{
        Authorization:`Bearer ${token}`,
        "Content-Type":"application/json"
      }
    })
    .then(res=>setuser(res.data))
    .catch(e=>console.log)
    
    
  }, [])
  
  return (
    <div className=" patient-dashboard">
      {/* Sidebar */}
      <PatientNavBar />

      {/* Main Content */}
      <div className="flex-grow-1 ">
        <Header userName={`${user.firstName} ${user.lastName}`} userType="Patient" />
        
        <div className="container main-content mt-4">
          <Profile profile={user} />
        </div>
      </div>
    </div>
  );
};

export default PatientDashboard;
