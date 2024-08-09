import axios from 'axios';

const BASE_URL = 'http://localhost:8082/gitartsy/api';

// Fetch all commission requests
export const getAllRequests = async () => {
    try {
        const response = await axios.get(BASEAPIURL);
        return response.data;
    } catch (error) {
        console.error('There was an error fetching commission requests', error);
        throw error;
    }
};

// Create a new commission request
export const createRequest = async (newRequest) => {
    try {
        const response = await axios.post(BASEAPIURL, newRequest);
        return response.data;
    } catch (error) {
        console.error('There was an error creating the commission request', error);
        throw error;
    }
};

// Update an existing commission request by ID
export const updateRequest = async (id, updatedRequest) => {
    try {
        const response = await axios.put(`${BASEAPIURL}/${id}`, updatedRequest);
        return response.data;
    } catch (error) {
        console.error('There was an error updating the commission request', error);
        throw error;
    }
};

// Delete a commission request by ID
export const deleteRequest = async (id) => {
    try {
        const response = await axios.delete(`${BASEAPIURL}/${id}`);
        return response.data;
    } catch (error) {
        console.error('There was an error deleting the commission request', error);
        throw error;
    }
};
