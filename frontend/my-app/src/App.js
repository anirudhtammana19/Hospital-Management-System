import React from 'react';
import './App.css';
import { Link, useLocation } from 'react-router-dom';
import Routing from './Routing'; // Import Routing component that contains routes
import axios from 'axios'
import PatientDashboard from './Component/Patient/PatientDashboard';
import Login from './Component/User/Login';
const App = () => {
  const location = useLocation();

  // Check if the current route is login or signup
  const isLoginPage = location.pathname === '/login';
  const isSignupPage = location.pathname === '/signup';
    

  return (
    < >
      {/*
      {!isLoginPage && !isSignupPage && (
        <header className="navbar">
          <Link to="/login">
            <button className='button'>Login</button>
          </Link>
          <Link to="/signup">
            <button className='button'>Signup</button>
          </Link>
        </header>
      )}
      <main>
        <Routing />
      </main>*/}
      <Routing/>
    </>
  );
};

export default App;
