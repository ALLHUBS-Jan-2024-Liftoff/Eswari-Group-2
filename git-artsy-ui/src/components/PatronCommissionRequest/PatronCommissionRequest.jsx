import React, { useState } from 'react';
import { createRequest } from '../../services/PatronCommissionService'; // Import your service
import './PatronCommissionRequest.css';

const PatronCommissionRequest = () => {
    const [newRequest, setNewRequest] = useState({
        fromUserId: '',
        toUserId: '',
        request: '',
        detail: '',
        description: '',
        subject: ''
    });

    const handleCreate = async (e) => {
        e.preventDefault();
        try {
            const createdRequest = await createRequest(newRequest); // Use the service method
            console.log('Request created successfully:', createdRequest);
            setNewRequest({
                fromUserId: '',
                toUserId: '',
                request: '',
                detail: '',
                description: '',
                subject: ''
            });
        } catch (error) {
            console.error('Error creating commission request:', error);
        }
    };

    return (
        <div className="container">
            <h2 className="title">Request A Commission</h2>
            <form onSubmit={handleCreate}>
                <div className="form-group">
                    <label>Subject</label>
                    <input
                        type="text"
                        value={newRequest.subject}
                        onChange={(e) => setNewRequest({ ...newRequest, subject: e.target.value })}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Details (e.g., Painting, Drawing, Mural, etc.)</label>
                    <input
                        type="text"
                        value={newRequest.request}
                        onChange={(e) => setNewRequest({ ...newRequest, request: e.target.value })}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Description (e.g., Disney, Bats, Soccer, etc.)</label>
                    <textarea
                        value={newRequest.description}
                        onChange={(e) => setNewRequest({ ...newRequest, description: e.target.value })}
                        required
                    />
                </div>
                <button type="submit" className="submit-button">Submit Request</button>
            </form>
        </div>
    );
};

export default PatronCommissionRequest;
