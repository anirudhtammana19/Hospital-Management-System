import React from 'react';
import './Service.css';

const Services = () => {
  const services = [
    { title: 'Dermatologist', image: 'path/to/image1.png' },
    { title: 'Neurosciences', image: 'path/to/image2.png' },
    { title: 'Cardiology', image: 'path/to/image3.png' },
  ];

  return (
    <section className="services">
      <h2>Our Services</h2>
      <div className="services-grid">
        {services.map((service, index) => (
          <div key={index} className="service-card">
            <img src={service.image} alt={service.title} />
            <h3>{service.title}</h3>
          </div>
        ))}
      </div>
    </section>
  );
};

export default Services;
