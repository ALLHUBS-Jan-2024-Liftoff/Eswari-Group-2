import React, { useState } from 'react';
import { createRequest } from '../../services/PatronCommissionService'; 
import './PatronCommissionRequest.css'; 
import Banner from '../Banner';

const PatronCommissionRequest = () => {
    // State to hold the form input values for creating a new request
    const [newRequest, setNewRequest] = useState({
        fromUserId: '',    // ID of the user making the request
        toUserId: '',      // ID of the artist receiving the request
        request: '',       // The type of request (e.g., Painting, Drawing, Mural)
        detail: '',        // Additional details for the request
        description: '',   // Description of the request
        subject: ''        // Subject line for the request
    });

    // Handler for creating a new commission request
    const handleCreate = async (e) => {
        e.preventDefault(); // Prevent the default form submission
        try {
            // Call the service method to create a new request
            const createdRequest = await createRequest(newRequest);
            console.log('Request created successfully:', createdRequest); // Log success message

            // Reset the form fields after successful creation
            setNewRequest({
                fromUserId: '',
                toUserId: '',
                request: '',
                detail: '',
                description: '',
                subject: ''
            });
        } catch (error) {
            console.error('Error creating commission request:', error); // Log error message
        }
    };

    return (
        <div className="container">
            <div>
             <Banner />
             </div>
            <h2 className="title">Request A Commission</h2> {/* Title at the top */}
            <form onSubmit={handleCreate}> {/* Form for creating a new request */}
                <div className="form-group">
                    <label>Subject</label> {/* Label for the subject field */}
                    <input
                        type="text"
                        value={newRequest.subject} // Bind input value to state
                        onChange={(e) => setNewRequest({ ...newRequest, subject: e.target.value })} // Update state on change
                        required // Make this field required
                    />
                </div>
                <div className="form-group">
                    <label>Details (e.g., Painting, Drawing, Mural, etc.)</label> {/* Label for the request field */}
                    <input
                        type="text"
                        value={newRequest.request} // Bind input value to state
                        onChange={(e) => setNewRequest({ ...newRequest, request: e.target.value })} // Update state on change
                        required // Make this field required
                    />
                </div>
                <div className="form-group">
                    <label>Description (e.g., Disney, Bats, Soccer, etc.)</label> {/* Label for the description field */}
                    <textarea
                        value={newRequest.description} // Bind textarea value to state
                        onChange={(e) => setNewRequest({ ...newRequest, description: e.target.value })} // Update state on change
                        required // Make this field required
                    />
                </div>
                <button type="submit" className="submit-button">Submit Request</button> {/* Submit button */}
            </form>
        </div>
    );
};

export default PatronCommissionRequest;
