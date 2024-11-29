import React from 'react';
import './Footer.css';

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">
        <div className="footer-logo">
          <h3>Amaze Care</h3>
        </div>

        <div className="footer-links">
          <h4>Quick Links</h4>
          <ul>
            <li><a href="#home">Home</a></li>
            <li><a href="#appointment">Appointment</a></li>
            <li><a href="#service">Service</a></li>
            <li><a href="#about">About Us</a></li>
            <li><a href="#contact">Contact Us</a></li>
          </ul>
        </div>

        <div className="footer-hours">
          <h4>Hours</h4>
          <ul>
            <li>Monday: 9:00 - 18:00</li>
            <li>Tuesday: 9:00 - 18:00</li>
            <li>Wednesday: 9:00 - 18:00</li>
            <li>Thursday: 9:00 - 18:00</li>
            <li>Friday: 9:00 - 18:00</li>
          </ul>
        </div>

        <div className="footer-contact">
          <h4>Contact</h4>
          <ul>
            <li>📞 000-000-000</li>
            <li>✉️ info@email.com</li>
            <li>📍 Kalutara South</li>
          </ul>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
