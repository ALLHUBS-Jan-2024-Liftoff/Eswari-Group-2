import Banner from "../Banner";
import React, { useState, useEffect } from 'react';
import profileService from '../../services/profileService'; 
import '../ProfilePage/ProfilePage.css'; 

const ProfilePage = () => {
  const [formData, setFormData] = useState({
    name: '',
    location: '',
    email: '',
    phone: '',
    bioDescription: ''
  });
  const [selectedFile, setSelectedFile] = useState(null);
  const [preview, setPreview] = useState('');
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [user, setUser] = useState(null);
  const [profile, setProfile] = useState(null); 
  const [isEditing, setIsEditing] = useState(false); // State to toggle between view and edit mode

  useEffect(() => {
    const userData = JSON.parse(localStorage.getItem('user'));
    if (userData) {
      setUser(userData);
      checkUserProfile(userData.userid);
    } else {
      console.log("No user data found in local storage.");
    }
  }, []);

  const checkUserProfile = async (userId) => {
    try {
      console.log("Checking profile for user ID:", userId);
      const exists = await profileService.checkProfileExists(userId);
      console.log("Profile exists:", exists);
      if (exists) {
        const profileData = await profileService.getProfile(userId);
        console.log("Profile data:", profileData);
        setProfile(profileData);
        setFormData({
          name: profileData.name,
          location: profileData.location,
          email: profileData.email,
          phone: profileData.phone,
          bioDescription: profileData.bioDescription
        });
        setPreview(profileData.fileDownloadUri); // Set existing profile picture as preview
      } else {
        console.log("Profile does not exist.");
      }
    } catch (error) {
      console.error("Error checking profile:", error);
    }
    console.log("User state after API call:", user);
    console.log("Profile state after API call:", profile);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevData => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setSelectedFile(file);

    if (file) {
      setPreview(URL.createObjectURL(file));
      setError("");
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!selectedFile && !profile) {
      setError("Please select a profile picture.");
      return;
    }

    const formDataToSend = new FormData();
    formDataToSend.append('userId', user.userid);
    formDataToSend.append('name', formData.name);
    formDataToSend.append('location', formData.location);
    formDataToSend.append('email', formData.email);
    formDataToSend.append('phone', formData.phone);
    formDataToSend.append('bioDescription', formData.bioDescription);
    if (selectedFile) {
      formDataToSend.append('profilePic', selectedFile);
    }

    try {
      if (isEditing) {
        await profileService.updateProfile(formDataToSend); // Update profile if editing
        alert('Profile updated successfully');
      } else {
        await profileService.createProfile(formDataToSend); // Create new profile if not editing
        alert('Profile created successfully');
      }
      setSuccess(isEditing ? "Profile updated successfully!" : "Profile created successfully!");
      setProfile(null);
      setSelectedFile(null);
      setPreview('');
    } catch (error) {
      console.error(isEditing ? "Error updating profile:" : "Error creating profile:", error);
      setError("An error occurred while saving the profile.");
      setSuccess("");
    }
  };

  return (
    <div className="app-container">
      <Banner />
      <div className="profile-container">
        {profile && !isEditing ? (
          <div className="view-profile-container">
            <h1>Your Profile</h1>
            <img src={profile.fileDownloadUri} alt="Profile" style={{ width: '200px', height: 'auto' }} />
            <p>Name: {profile.name}</p>
            <p>Location: {profile.location}</p>
            <p>Email: {profile.email}</p>
            <p>Phone: {profile.phone}</p>
            <p>Bio: {profile.bioDescription}</p>
            <button onClick={() => setIsEditing(true)}>Edit Profile</button>
          </div>
        ) : (
          <div className="create-profile-container">
            <h1>{isEditing ? "Edit Profile" : "Create Profile"}</h1>
            {user && (
              <>
                <form onSubmit={handleSubmit}>
                  <label>
                    Name:
                    <input type="text" name="name" value={formData.name} onChange={handleChange} required />
                  </label>
                  <br />
                  <label>
                    Location:
                    <input type="text" name="location" value={formData.location} onChange={handleChange} required />
                  </label>
                  <br />
                  <label>
                    Email:
                    <input type="email" name="email" value={formData.email} onChange={handleChange} required />
                  </label>
                  <br />
                  <label>
                    Phone:
                    <input type="text" name="phone" value={formData.phone} onChange={handleChange} required />
                  </label>
                  <br />
                  <label>
                    Bio Description:
                    <textarea name="bioDescription" value={formData.bioDescription} onChange={handleChange} required />
                  </label>
                  <br />
                  <label>
                    Profile Picture:
                    <input type="file" name="profilePic" onChange={handleFileChange} />
                  </label>
                  {preview && <img src={preview} alt="Preview" style={{ marginTop: '10px', width: '200px', height: 'auto' }} />}
                  <br />
                  <button type="submit">{isEditing ? "Update Profile" : "Create Profile"}</button>
                </form>
                {error && <p className="error-message">{error}</p>}
                {success && <p className="success-message">{success}</p>}
              </>
            )}
            {!user && <p>No profile added yet.</p>}
          </div>
        )}
      </div>
    </div>
  );
};

export default ProfilePage;
