import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import './App.css';

import LandingLogin from './components/LoginLanding';
import ArtworksPopup from './components/LoginLandingPage/Artwork';

const App = () => {
  

  return (
    <div>
      <LandingLogin />
      {/* <ArtworksPopup /> */}
    </div>
  );
};

export default App;
