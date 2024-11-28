import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './Component/User/Login';
  // Ensure this path is correct
import Register from './Component/User/Register';

const routing = () => {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      
      <Route path="/Signup" element={<Register/>}/>
    </Routes>
  );
};

export default routing;
