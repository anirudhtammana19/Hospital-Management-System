import React from 'react';
import './NavBar.css';
import Login from '../User/Login';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <header className="navheader">
      <div className="logo">Amaze Care</div>
      <div className="nav-buttons">
        <Link to={"/login"}><button className="sdn btn-signin">Sign In</button></Link>
        <Link to={"/signup"}><button className="sdn btn-register">Register</button></Link>
      </div>
    </header>
  );
};

export default Header;
