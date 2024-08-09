import React, { useState, useEffect } from 'react';
import { getAllRequests, createRequest, updateRequest, deleteRequest } from "../../services/PatronCommissionService"; 
//"../../services/userService"

import axios from 'axios';
import App from '../../App';

const BASE_URL = 'http://localhost:8082/gitartsy/api';

const PatronCommissionRequest = () => {
    // State to hold commission requests fetched from the API
    const [requests, setRequests] = useState([]);

    // State for form inputs
    const [newRequest, setNewRequest] = useState({
        fromUserId: '',
        toUserId: '',
        request: '',
        detail: '',
        description: '',
        subject: ''
    });

    // Fetch all requests on component mount
    useEffect(() => {
        const fetchRequests = async () => {
            try {
                const data = await getAllRequests();
                setRequests(data); // Set the fetched requests to state
            } catch (error) {
                console.error('Error fetching commission requests:', error);
            }
        };
        fetchRequests();
    }, []);

    // Handler for creating a new request
    const handleCreate = async (e) => {
        e.preventDefault(); // Prevent default form submission behavior
        try {
            const createdRequest = await createRequest(newRequest);
            setRequests([...requests, createdRequest]); // Add the new request to the state
            setNewRequest({
                fromUserId: '',
                toUserId: '',
                request: '',
                detail: '',
                description: '',
                subject: ''
            }); // Reset form fields
        } catch (error) {
            console.error('Error creating commission request:', error);
        }
    };

    // Handler for updating an existing request
    const handleUpdate = async (id, updatedRequest) => {
        try {
            const updated = await updateRequest(id, updatedRequest);
            setRequests(requests.map(req => (req.id === id ? updated : req))); // Update the request in the state
        } catch (error) {
            console.error('Error updating commission request:', error);
        }
    };

    // Handler for deleting a request
    const handleDelete = async (id) => {
        try {
            await deleteRequest(id);
            setRequests(requests.filter(req => req.id !== id)); // Remove the deleted request from the state
        } catch (error) {
            console.error('Error deleting commission request:', error);
        }
    };

    return (
        <div>
            <h2>Commission Requests</h2>
            {/* Form for creating new requests */}
            <form onSubmit={handleCreate}>
                <input
                    type="number"
                    placeholder="From User ID"
                    value={newRequest.fromUserId}
                    onChange={(e) => setNewRequest({ ...newRequest, fromUserId: e.target.value })}
                    required
                />
                <input
                    type="number"
                    placeholder="To User ID"
                    value={newRequest.toUserId}
                    onChange={(e) => setNewRequest({ ...newRequest, toUserId: e.target.value })}
                    required
                />
                <input
                    type="text"
                    placeholder="Request"
                    value={newRequest.request}
                    onChange={(e) => setNewRequest({ ...newRequest, request: e.target.value })}
                    required
                />
                <input
                    type="text"
                    placeholder="Detail"
                    value={newRequest.detail}
                    onChange={(e) => setNewRequest({ ...newRequest, detail: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="Description"
                    value={newRequest.description}
                    onChange={(e) => setNewRequest({ ...newRequest, description: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="Subject"
                    value={newRequest.subject}
                    onChange={(e) => setNewRequest({ ...newRequest, subject: e.target.value })}
                />
                <button type="submit">Create Request</button>
            </form>

            {/* List of existing requests */}
            <ul>
                {requests.map((request) => (
                    <li key={request.id}>
                        <strong>From User ID:</strong> {request.fromUserId} <br />
                        <strong>To User ID:</strong> {request.toUserId} <br />
                        <strong>Request:</strong> {request.request} <br />
                        <strong>Detail:</strong> {request.detail} <br />
                        <strong>Description:</strong> {request.description} <br />
                        <strong>Subject:</strong> {request.subject} <br />
                        {/* Buttons for updating and deleting requests */}
                        <button onClick={() => handleUpdate(request.id, { ...request, request: 'Updated request details' })}>Update</button>
                        <button onClick={() => handleDelete(request.id)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default PatronCommissionRequest;
