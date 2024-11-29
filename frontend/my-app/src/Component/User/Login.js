
import React, { useState } from 'react';
import { MDBContainer, MDBCol, MDBRow, MDBBtn, MDBIcon, MDBInput, MDBCheckbox } from 'mdb-react-ui-kit';
import "./Login.css"
import axios from 'axios'
import { useNavigate } from 'react-router-dom';

function Login() {

  let nav=useNavigate()
  const [email, setemail] = useState('')
  const [password, setpassword] = useState('')
  const logindata={username:email,password}
  const handleLogin=()=>{
      axios.post("http://localhost:8080/api/login",logindata)
      .then(res=>{
        localStorage.setItem('token',res.data.jwt)
        localStorage.setItem('userid',res.data.userid)
        localStorage.setItem('username',res.data.username)
        localStorage.setItem('role',res.data.role)
        nav("/patient/dashboard")
      })
      .catch(e=>console.log(e))

  }

  return (<>
    <MDBContainer fluid className="p-3 my-5 h-custom">

      <MDBRow>

        <MDBCol col='10' md='6'>
          
        </MDBCol>

        <MDBCol col='4' md='6'>

            <br />
            <br />
            <br />
            <br />
            <br />

          {/* Company Name */}
          <div className="mb-4" style={{ fontFamily: 'Arial, sans-serif', fontWeight: 'bold', fontSize: '2.5rem', color: '#333' }}>
  Amaze Care 
  <h4 style={{ fontWeight: 'normal', fontSize: '1.2rem', color: '#777', marginTop: '5px' }}>
    Make your health journey easy
  </h4>
</div>


          

          

          <div className="d-flex justify-content-between mb-4">
            <MDBCheckbox name='flexCheck' value='' id='flexCheckDefault' label='Remember me' />
            <p>Forgot password?</p>
          </div>

          <div className='text-center text-md-start mt-4 pt-2' style={{ textAlign: 'center' }}>
          <MDBInput wrapperClass='mb-4' label='Email address' id='formControlLg' 
          type='email' size="lg" onChange={e=>setemail(e.target.value)}/>
          <MDBInput wrapperClass='mb-4' label='Email Password' id='formControlLg' 
          type='password' size="lg"  onChange={e=>setpassword(e.target.value)}/>
  <MDBBtn className="mb-0 px-5" size='lg' style={{ display: 'block', margin: '0 auto' }} onClick={handleLogin}>Login</MDBBtn>
  <div className="small fw-bold mt-2 pt-1 mb-2">
    Don't have an account? <p className="link-danger">Register</p>
  </div>
</div>


        </MDBCol>

      </MDBRow>

      <div className="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
        <div className="text-white mb-3 mb-md-0">
          Copyright © 2020. All rights reserved.
        </div>
      </div>

    </MDBContainer>
    </>
  );
}
export default Login;