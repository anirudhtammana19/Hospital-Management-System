import React from 'react';
import './App.css';
import { BrowserRouter as Router, Link } from 'react-router-dom';
import Routing from './Routing'; // Import Routing component that contains routes
import axios from 'axios'
const App = () => {

  const sample=()=>{
const user={
  username:"admin",
  password:"admin123"
}
    axios.post("http://localhost:8080/api/login",user)
    .then(res=>{
      console.log(res.data.jwt)
      console.log(res.data.role)
      
    })
    .catch(e=>console.log(e))
  }
  return (
    <> 
    
    
{/*
    <Router>
      <div className='div1'>
        
        <Link to="/login">
          <button className='button'>Login</button>
          <button className='button'>Sign up</button>
        </Link>
        <Routing />
      </div>
    </Router>
  */ 
  }
  <button onClick={sample}>Try</button>
  </>
  );
};
export default App;