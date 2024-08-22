//BY VAISHNAVI

import axios from 'axios';

const BASE_URL = 'http://localhost:8082/gitartsy/api/tags';

// API methods for tag operations
const getAllTags = async () => {
    try {
      const response = await axios.get(BASE_URL);
      
      return Array.isArray(response.data) ? response.data : [];
    } catch (error) {
      console.error('Error fetching tags:', error);
      return [];
    }
  };

const getTagById = (id) => {
    return axios.get(`${BASE_URL}/${id}`)
        .catch(error => {
            console.error(`Error fetching tag with ID ${id}:`, error);
            throw error;
        });
};

const createTag = (tagData) => {
    return axios.post(BASE_URL, tagData)
        .catch(error => {
            console.error("Error creating tag:", error);
            throw error;
        });
};

const updateTag = (id, tagData) => {
    return axios.put(`${BASE_URL}/${id}`, tagData)
        .catch(error => {
            console.error(`Error updating tag with ID ${id}:`, error);
            throw error;
        });
};

const deleteTag = (id) => {
    return axios.delete(`${BASE_URL}/${id}`)
        .catch(error => {
            console.error(`Error deleting tag with ID ${id}:`, error);
            throw error;
        });
};

export default {
    getAllTags,
    getTagById,
    createTag,
    updateTag,
    deleteTag
};