import React from 'react';
import './App.css';
import { BrowserRouter as Router, Link } from 'react-router-dom';
import Routing from './Routing'; // Import Routing component that contains routes

const App = () => {
  return (

    <Router>
      <div className='div1'>
        {/* Navigation buttons */}
        <Link to="/login">
          <button className='button'>Login</button>
          <button className='button'>Sign up</button>
        </Link>

        {/* Render Routes */}
        <Routing />
      </div>
    </Router>
  );
};
export default App;