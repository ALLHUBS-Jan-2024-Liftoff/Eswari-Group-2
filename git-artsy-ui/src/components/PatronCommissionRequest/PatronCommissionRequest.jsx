import React, { useState } from 'react';
import { createRequest } from '../../services/PatronCommissionService'; 
import './PatronCommissionRequest.css'; 
import Banner from '../Banner';
import axios from 'axios';

const PatronCommissionRequest = () => {
    // State to hold the form input values for creating a new request
  const [toUserId, setToUserId] = useState('');
  const [subject, setSubject] = useState('');
  const [details, setDetails] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Prepare the data object
      const data = {
        artist_id: toUserId,         
        subject: subject,
        details: details,
        description: description,
      };

      // Make the axios POST request
      const response = await axios.post('http://localhost:8082/api/commissions/submit', data);
      console.log('Request submitted successfully:', response.data);
    } catch (error) {
      console.error('Error submitting request:', error);
    }
  };

    return (
        <div className="container-commissions">
            <Banner />
            <h2 className="title">Request A Commission</h2>
            <form onSubmit={handleSubmit}>

            <div className="form-group">
                    <label>Artist User ID</label>
                    <input
                        type="text"
                        value={toUserId}
                        onChange={(e) => setToUserId(e.target.value)}
                        required
                    />
                </div>
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
                        value={details}
                        onChange={(e) => setDetails(e.target.value)}
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
            {/* {message && <p>{message}</p>} */}
        </div>
    );
};

export default PatronCommissionRequest;
