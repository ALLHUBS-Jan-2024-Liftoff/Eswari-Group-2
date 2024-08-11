import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import LandingLogin from './components/LoginLandingPage/LoginLanding';
import PatronCommissionRequest from './components/PatronCommissionRequest/PatronCommissionRequest';


const App = () => {
  

  return (
    <>
      <LandingLogin />
      <PatronCommissionRequest />
      </>
  
  );
};

export default App;
