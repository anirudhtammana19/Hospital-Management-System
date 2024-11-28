import React, { useState } from 'react';
import { Button } from 'semantic-ui-react';
import SignupForm from './PatientSignUp';




const Register = () => {
  const [modalOpen, setModalOpen] = useState(false);

  const openModal = () => setModalOpen(true);
  const closeModal = () => setModalOpen(false);

  return (
    <div>
      <div className='div1'>
      <button className='button' onClick={openModal}>Next</button>

      <SignupForm open={modalOpen} handleClose={closeModal} />
    </div></div>
  );
};

export default Register;