import React from 'react';
import Login from './LoginLandingPage/Login';
import ArtworksPopup from './LoginLandingPage/Artwork';

const LandingLogin = () => {
  return (
    <div className="app-container">
        {/* Header Part Coding */}
      <div className="login-container">
         <Login /> 
      </div>
      <div className="popup-container">
        <ArtworksPopup />
      </div>
      {/* Footer Part */}
    </div>
  );
};

export default LandingLogin;
