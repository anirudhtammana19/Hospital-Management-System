
import React from 'react';
import { MDBContainer, MDBCol, MDBRow, MDBBtn, MDBIcon, MDBInput, MDBCheckbox } from 'mdb-react-ui-kit';
import "./Login.css"

function Login() {
  return (
    <MDBContainer fluid className="p-3 my-5 h-custom">

      <MDBRow>

        <MDBCol col='10' md='6'>
          <img src="./Doctor Login.jpg" className="img-fluid" alt="Sample image" />
        </MDBCol>

        <MDBCol col='4' md='6'>

            <br />
            <br />
            <br />
            <br />
            <br />

          {/* Company Name */}
          <h2 className="mb-4" style={{ fontFamily: 'Arial, sans-serif', fontWeight: 'bold', fontSize: '2.5rem', color: '#333' }}>
  Amaze Care 
  <h4 style={{ fontWeight: 'normal', fontSize: '1.2rem', color: '#777', marginTop: '5px' }}>
    Make your health journey easy
  </h4>
</h2>


          <MDBInput wrapperClass='mb-4' label='Email address' id='formControlLg' type='email' size="lg"/>
          <MDBInput wrapperClass='mb-4' label='Password' id='formControlLg' type='password' size="lg"/>

          <div className="d-flex justify-content-between mb-4">
            <MDBCheckbox name='flexCheck' value='' id='flexCheckDefault' label='Remember me' />
            <a href="!#">Forgot password?</a>
          </div>

          <div className='text-center text-md-start mt-4 pt-2' style={{ textAlign: 'center' }}>
  <MDBBtn className="mb-0 px-5" size='lg' style={{ display: 'block', margin: '0 auto' }}>Login</MDBBtn>
  <p className="small fw-bold mt-2 pt-1 mb-2">
    Don't have an account? <a href="#!" className="link-danger">Register</a>
  </p>
</div>


        </MDBCol>

      </MDBRow>

      <div className="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
        <div className="text-white mb-3 mb-md-0">
          Copyright © 2020. All rights reserved.
        </div>
      </div>

    </MDBContainer>
  );
}
export default Login;