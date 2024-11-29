import React from "react";
import { Navbar, Button } from "react-bootstrap";
import "./Header.css";

const Header = ({ userName, userType }) => {
  return (
    <Navbar bg="white" className="shadow-sm header px-3">
      <Navbar.Brand className="text-primary fw-bold">Amaze Care</Navbar.Brand>
      <Navbar.Collapse className="justify-content-end">
        <Navbar.Text className="me-3">
          <div>{userName}</div>
          <small>{userType}</small>
        </Navbar.Text>
        <Button variant="outline-danger" size="sm">
          Logout
        </Button>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default Header;
