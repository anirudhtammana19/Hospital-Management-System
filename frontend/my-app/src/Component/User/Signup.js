import React, { useState } from 'react';
import {
  MDBContainer,
  MDBCol,
  MDBRow,
  MDBBtn,
  MDBInput,
} from 'mdb-react-ui-kit';
import SignupForm from './PatientSignUp';
import './Login.css';
import {message} from 'antd';

function Signup() {
  const [modalOpen, setModalOpen] = useState(false);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleRegisterClick = () => {
    if (!email || !password || !confirmPassword) {
      message.warning("Mandatory fields are requried")
    } else if (password !== confirmPassword) {
      message.warning("Passwords do not match")
    } else {
      setModalOpen(true); // Open modal if validation passes
    }
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  return (
    <>
    <MDBContainer fluid className="p-3 my-5 h-custom">
      <MDBRow>
        <MDBCol col="10" md="6" className="d-flex justify-content-center align-items-center">
          <img
            src="./Signup Image.jpg"
            className="img-fluid"
            alt="Sample image"
            style={{ maxWidth: '80%', height: 'auto' }}
          />
        </MDBCol>

        

        <MDBCol col="4" md="6">
        <br></br>
          <h2
            className="mb-4"
            style={{
              fontFamily: 'Arial, sans-serif',
              fontWeight: 'bold',
              fontSize: '2.5rem',
              color: '#333',
            }}
          >
            Amaze Care
            <h3
              style={{
                fontWeight: 'normal',
                fontSize: '1.2rem',
                color: '#777',
                marginTop: '5px',
              }}
            >
              Register For a Healthy Life
            </h3>
          </h2>
          <br></br>
          
          <MDBInput
            wrapperClass="mb-4"
            label="Email address"
            id="email"
            type="email"
            size="lg"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <MDBInput
            wrapperClass="mb-4"
            label="Password"
            id="password"
            type="password"
            size="lg"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <MDBInput
            wrapperClass="mb-4"
            label="Confirm Password"
            id="confirmPassword"
            type="password"
            size="lg"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />

          <div className="text-center text-md-start mt-4 pt-2">
            <MDBBtn
              className="mb-0 px-5"
              size="lg"
              style={{ display: 'block', margin: '0 auto' }}
              onClick={handleRegisterClick}
            >
              Register
            </MDBBtn>
          </div>
        </MDBCol>
      </MDBRow>

     

<br/>
      <div className="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
        <div className="text-white mb-3 mb-md-0">
          Copyright © 2020. All rights reserved.
        </div>
      


</div >
    </MDBContainer>
    <SignupForm className="sigup"
open={modalOpen}
handleClose={closeModal}
email={email}
password={password}
/>
    </>
  );
}

export default Signup;
