import React, { useState } from 'react';
import { createRequest } from '../../services/PatronCommissionService'; 
import './PatronCommissionRequest.css'; 
import Banner from '../Banner';

const PatronCommissionRequest = () => {
    // State to hold the form input values for creating a new request
    const [fromUserId, setFromUserId] = useState('');
    const [toUserId, setToUserId] = useState('');
    const [requestType, setRequestType] = useState('');
    const [detail, setDetail] = useState('');
    const [description, setDescription] = useState('');
    const [subject, setSubject] = useState('');
    const [message, setMessage] = useState('');

    // Handler for creating a new commission request
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await createRequest({ fromUserId, toUserId, requestType, detail, description, subject });
            setMessage("Commission Sent!");
            window.location.href = "/";
        } catch (error) {
            setMessage(error.response?.data?.message || 'Error creating commission request');
        }
    };

    return (
        <div className="container-commission">
            <Banner />
            <h2 className="title">Request A Commission</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Subject</label>
                    <input
                        type="text"
                        value={subject}
                        onChange={(e) => setSubject(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Details (e.g., Painting, Drawing, Mural, etc.)</label>
                    <input
                        type="text"
                        value={requestType}
                        onChange={(e) => setRequestType(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Description (e.g., Disney, Bats, Soccer, etc.)</label>
                    <textarea
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="submit-button">Submit Request</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
};

export default PatronCommissionRequest;
