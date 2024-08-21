import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom'; // to get profileId from the URL
import profileService from '../../services/profileService'; 
import Banner from "../Banner";
import Follow from './Follow';

const ViewProfile = () => {
  const { profileId } = useParams(); // fetches profileId from the route parameters
  const [profile, setProfile] = useState(null); 
  const [error, setError] = useState("");
  const [preview, setPreview] = useState(''); 
  const currentUser = JSON.parse(localStorage.getItem('user'));

  // fetches profile data when profileId changes
  useEffect(() => {
    if (profileId) {
      fetchUserProfile(profileId);
    }
  }, [profileId]);

  // fetches the user's profile data
  const fetchUserProfile = async (profileId) => {
    try {
      const profileData = await profileService.getProfile(profileId);
      if (profileData) {
        setProfile(profileData);
        setPreview(profileData.fileDownloadUri); // sets profile picture as preview
      } else {
        setError('No profile found for this user.');
      }
    } catch (error) {
      console.error("Error fetching profile:", error);
      setError('Error fetching profile');
    }
  };

  return (
    <div className="app-container">
      <Banner />
      <div className="profile-container">
        {profile ? (
          <div className="view-profile-container">
            <h1>User Profile</h1>
            <img src={preview} alt="Profile" style={{ width: '200px', height: 'auto' }} />
            <p>Name: {profile.name}</p>
            <Follow />
            <p>Location: {profile.location}</p>
            <p>Email: {profile.email}</p>
            <p>Phone: {profile.phone}</p>
            <p>Bio: {profile.bioDescription}</p>
          </div>
        ) : (
          <div>
            {error ? <p>{error}</p> : <p>Loading profile...</p>}
          </div>
        )}
      </div>
    </div>
  );
};

export default ViewProfile;
