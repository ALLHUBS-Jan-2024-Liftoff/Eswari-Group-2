import axios from 'axios';

const BASE_URL = 'http://localhost:8082/gitartsy/api/commissions';

// Fetch all commission requests
export const getAllRequests = async () => {
    try {
        const response = await axios.get(BASE_URL);
        return response.data;
    } catch (error) {
        console.error('Error fetching commission requests:', error);
        throw error;
    }
};

// Create a new commission request
export const createRequest = async (newRequest) => {
    try {
        const response = await axios.post(BASE_URL, newRequest);
        return response.data;
    } catch (error) {
        console.error('Error creating commission request:', error);
        throw error;
    }
};

// Update an existing commission request by ID
export const updateRequest = async (id, updatedRequest) => {
    try {
        const response = await axios.put(`${BASE_URL}/${id}`, updatedRequest);
        return response.data;
    } catch (error) {
        console.error('Error updating commission request:', error);
        throw error;
    }
};

// Delete a commission request by ID
export const deleteRequest = async (id) => {
    try {
        const response = await axios.delete(`${BASE_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error('Error deleting commission request:', error);
        throw error;
    }
};
