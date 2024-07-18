import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import './App.css';

import LandingLogin from './components/LoginLandingPage/LoginLanding';


const App = () => {
  

  return (
    <div>
      <LandingLogin />
      {/* TODO add routes and paths 
      and add ArtworksPopup to Landing Login*/}
      {/* <ArtworksPopup /> */}
    </div>
  );
};

export default App;
