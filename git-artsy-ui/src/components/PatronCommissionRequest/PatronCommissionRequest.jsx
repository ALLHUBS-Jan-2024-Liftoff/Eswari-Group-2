import React, { useState, useEffect } from 'react';
import { getAllRequests, createRequest, updateRequest, deleteRequest } from "../../services/PatronCommissionService";

const PatronCommissionRequest = () => {
    // State to hold commission requests fetched from the API
    const [requests, setRequests] = useState([]);

    // State for form inputs to create a new commission request
    const [newRequest, setNewRequest] = useState({
        fromUserId: '',   // ID of the user sending the request
        toUserId: '',     // ID of the user receiving the request
        request: '',      // The main request content
        detail: '',       // Additional details of the request
        description: '',  // Description of the request
        subject: ''       // Subject of the request
    });

    // Fetch all requests on component mount
    useEffect(() => {
        const fetchRequests = async () => {
            try {
                // Fetching all commission requests from the API
                const data = await getAllRequests();
                // Setting the fetched requests to state
                setRequests(data);
            } catch (error) {
                // Logging any errors that occur during the fetch
                console.error('Error fetching commission requests:', error);
            }
        };
        // Triggering the fetch function
        fetchRequests();
    }, []);

    // Handler for creating a new request
    const handleCreate = async (e) => {
        e.preventDefault(); // Prevent default form submission behavior
        try {
            // Creating a new commission request via the API
            const createdRequest = await createRequest(newRequest);
            // Adding the new request to the state
            setRequests([...requests, createdRequest]);
            // Resetting form fields after submission
            setNewRequest({
                fromUserId: '',
                toUserId: '',
                request: '',
                detail: '',
                description: '',
                subject: ''
            });
        } catch (error) {
            // Logging any errors that occur during the creation
            console.error('Error creating commission request:', error);
        }
    };

    // Handler for updating an existing request
    const handleUpdate = async (id, updatedRequest) => {
        try {
            // Updating the commission request via the API
            const updated = await updateRequest(id, updatedRequest);
            // Updating the request in the state with the newly updated data
            setRequests(requests.map(req => (req.id === id ? updated : req)));
        } catch (error) {
            // Logging any errors that occur during the update
            console.error('Error updating commission request:', error);
        }
    };

    // Handler for deleting a request
    const handleDelete = async (id) => {
        try {
            // Deleting the commission request via the API
            await deleteRequest(id);
            // Removing the deleted request from the state
            setRequests(requests.filter(req => req.id !== id));
        } catch (error) {
            // Logging any errors that occur during the deletion
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
