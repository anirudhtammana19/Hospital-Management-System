import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './Component/User/Login';  // Ensure this path is correct

const routing = () => {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      {/* Add more routes as needed */}
    </Routes>
  );
};

export default routing;
