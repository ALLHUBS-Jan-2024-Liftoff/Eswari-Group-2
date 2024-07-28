import React, { useState, useEffect } from 'react';
import axios from'axios';


//Component for handling Patron Commission Requests
const PatronCommissionRequest = () => {
    //State to store the list of commission requests
    const [requests, setRequests] = useState([]);
    //State to store form data
    const [formData, setFormData] = useState({
        from: '',
        to: '',
        request: ''
    }, []);

    //Fetches all commission requests on component mount
    useEffect(() => {
        fetchRequests();
    });

    //Function to fetch all commission requests from the backend
    const fetchRequests = async => {
        try {
            const response = await axios.get('/api/commissions');
            setRequests(response.data);
        } catch (error) {
            console.error('Error fetching commission requests:', error);
        }
    };

    //Handles form submission to create a new commission request
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/api/commissions', formData);
            setRequests([...requests, response.data]);
            setFormData({ from: '', to: '', request: '' });
        } catch (error) {
            console.error('Error creating commission request:', error);
        }
    };

    return (
        <div className="commission-request">
            <h2>Commission Requests</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="from">From (User ID):</label>
                    <input
                        type="text"
                        id="from"
                        name="from"
                        value={formData.from}
                        onChange={handleChange}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="to">To (User ID):</label>
                    <input
                        type="text"
                        id="to"
                        name="to"
                        value={formData.to}
                        onChange={handleChange}
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="request">Request:</label>
                    <textarea
                        id="request"
                        name="request"
                        value={formData.request}
                        onChange={handleChange}
                    ></textarea>
                </div>
                <button type="submit">Submit Request</button>
            </form>
            <h3>Existing Requests</h3>
            <ul>
                {requests.map((req) => (
                    <li key={req.id}>
                        From: {req.from} To: {req.to} - {req.request}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default PatronCommissionRequest;
