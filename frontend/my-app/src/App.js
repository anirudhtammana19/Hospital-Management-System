import React from 'react';
import './App.css';
import { Link, useLocation } from 'react-router-dom';
import Routing from './Routing'; // Import Routing component that contains routes

const App = () => {
  const location = useLocation();

  // Check if the current route is login or signup
  const isLoginPage = location.pathname === '/login';
  const isSignupPage = location.pathname === '/signup';

  return (
    <div className='div1'>
      {/* Render Login and Signup buttons only if not on login or signup page */}
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

      {/* Main Content */}
      <main>
        <Routing />
      </main>
    </div>
  );
};

export default App;
