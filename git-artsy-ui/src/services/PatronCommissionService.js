import axios from 'axios';

// Base URL for the API endpoint
const BASE_URL = 'http://localhost:8082/gitartsy/api/commissions'; 

// Fetch all commission requests
export const getAllRequests = async () => {
    try {
        // Sending a GET request to fetch all commission requests
        const response = await axios.get(BASE_URL);
        // Returning the response data (an array of commission requests)
        return response.data;
    } catch (error) {
        // Logging any errors that occur during the fetch
        console.error('There was an error fetching commission requests', error);
        throw error;
    }
};

// Create a new commission request
export const createRequest = async (newRequest) => {
    try {
        // Sending a POST request to create a new commission request
        const response = await axios.post(BASE_URL, newRequest);
        // Returning the response data (the newly created commission request)
        return response.data;
    } catch (error) {
        // Logging any errors that occur during the creation
        console.error('There was an error creating the commission request', error);
        throw error;
    }
};

// Update an existing commission request by ID
export const updateRequest = async (id, updatedRequest) => {
    try {
        // Sending a PUT request to update an existing commission request by its ID
        const response = await axios.put(`${BASE_URL}/${id}`, updatedRequest);
        // Returning the response data (the updated commission request)
        return response.data;
    } catch (error) {
        // Logging any errors that occur during the update
        console.error('There was an error updating the commission request', error);
        throw error;
    }
};

// Delete a commission request by ID
export const deleteRequest = async (id) => {
    try {
        // Sending a DELETE request to delete an existing commission request by its ID
        const response = await axios.delete(`${BASE_URL}/${id}`);
        // Returning the response data (confirmation of deletion)
        return response.data;
    } catch (error) {
        // Logging any errors that occur during the deletion
        console.error('There was an error deleting the commission request', error);
        throw error;
    }
};
