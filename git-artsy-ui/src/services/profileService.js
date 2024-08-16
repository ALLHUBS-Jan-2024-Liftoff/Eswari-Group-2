import axios from 'axios';

const API_BASE_URL = 'http://localhost:8082/gitartsy/api/profiles';

const profileService = {
  
  createProfile: async (formData) => {
    try {
      const response = await axios.post(`${API_BASE_URL}/new`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      return response.data;
    } catch (error) {
      console.error("Error creating profile", error);
      throw error;
    }
  },

  checkProfileExists: async (userId) => {
    try {
      const response = await axios.get(`${API_BASE_URL}/exists/${userId}`);
      return response.data;
    } catch (error) {
      console.error("Error checking if profile exists", error);
      throw error;
    }
  },

  getProfile: async (userId) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/${userId}`);
      return response.data;
    } catch (error) {
      console.error("Error getting profile", error);
      throw error;
    }
  },

  updateProfile: async (profileId, formData) => {
    try {
      const response = await axios.put(`${API_BASE_URL}/update/${profileId}`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      return response.data;
    } catch (error) {
      console.error("Error updating profile", error);
      throw error;
    }
  }

  
};

export default profileService;
