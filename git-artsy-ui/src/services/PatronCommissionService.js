import axios from 'axios';

const BASE_URL = 'http://localhost:8082/api/commissions';

// Fetch all commission requests
export const getAllRequests = async () => {
    try {
        // Send a GET request to fetch all commission requests from the backend
        const response = await axios.get(BASE_URL);
        return response.data;
    } catch (error) {
        console.error('Error fetching commission requests:', error);
        throw error;
    }
};

// Create a new commission request
export const createRequest = async (toUserId, details, description, subject) => {
    try {
        // Send a POST request to create a new commission request
        const response = await axios.post(`${BASE_URL}/submit`, {
            //fromUserId,  // The ID of the user making the request
            toUserId,    // The ID of the artist receiving the request
            details,      // The type of artwork requested (e.g., Painting, Drawing)
            description, // The description of the request
            subject      // The subject of the commission
        });
        return response.data;
    } catch (error) {
        console.error('Error creating commission request:', error);
        throw error;
    }
};

// Update an existing commission request by ID
export const updateRequest = async (id, updateRequest) => {
    try {
        // Send a PUT request to update an existing commission request by its ID
        const response = await axios.put(`${BASE_URL}/${id}`, updateRequest);
        return response.data;
    } catch (error) {
        console.error('Error updating commission request:', error);
        throw error;
    }
};

// Delete a commission request by ID
export const deleteRequest = async (id) => {
    try {
        // Send a DELETE request to delete a commission request by its ID
        const response = await axios.delete(`${BASE_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error('Error deleting commission request:', error);
        throw error;
    }
};
