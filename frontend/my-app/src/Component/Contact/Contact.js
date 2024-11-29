import React from 'react';
import './Contact.css';

const Contact = () => {
  return (
    <section className="contact-form">
      <h2>Send Us a Message</h2>
      <form>
        <div className="form-row">
          <input type="text" placeholder="First Name" required />
          <input type="text" placeholder="Last Name" required />
        </div>
        <div className="form-row">
          <input type="text" placeholder="Mobile Number" required />
          <input type="email" placeholder="Email" required />
        </div>
        <textarea placeholder="Message"></textarea>
        <button type="submit">Send</button>
      </form>
    </section>
  );
};

export default Contact;
