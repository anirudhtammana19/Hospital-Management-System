import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './Component/User/Login';
  // Ensure this path is correct

import Signup from './Component/User/Signup';
import Home from './Component/Home/Home';

const routing = () => {
  return (
    <Routes>


      <Route path="/" element={<Home/>} />      
      <Route path="/login" element={<Login />} />
      
      <Route path="/signup" element={<Signup/>}/>

      
    </Routes>
  );
};

export default routing;
