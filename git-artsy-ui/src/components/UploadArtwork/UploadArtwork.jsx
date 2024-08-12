import Banner from "../Banner";

import React, { useState, useEffect } from 'react';
import api from '../../services/artworkService.js';
import { useNavigate } from 'react-router-dom';
import '../UploadArtwork/UploadArtwork.css';
import tagservice from '../../services/TagService.js'


const UploadArtwork = ({onUploadSuccess}) => {
    // State variables
    const [formData, setFormData] = useState({
        title: '',
        description: '',
        price: '',
        tagIds: []
    });


    const [tags, setTags] = useState([]);
    const [selectedFile, setSelectedFile] = useState(null);
    const [uploading, setUploading] = useState(false);
    const [error, setError] = useState("");
    const navigate = useNavigate();
    const [user, setUser] = useState(null);
    const [profileId, setProfileId] = useState(null); // Add state for profileId
    const [preview, setPreview] = useState("");


    const fetchProfileId = async (userId) => {
        try {
            const response = await fetch(`http://localhost:8082/gitartsy/api/profiles/profileid/${userId}`);
            const data = await response.json();
            
            if (data !== 0) {
                setProfileId(data);
            } else {
                console.error('Profile not found for the user');
                setError('Profile not found');
            }
        } catch (error) {
            console.error("Error fetching profile ID:", error);
            setError('Error fetching profile ID');
        }
    };


    // useEffect hook to fetch tags and user data
    useEffect(() => {
        const fetchTags = async () => {
            try {
                const response = await tagservice.getAllTags();
                
                setTags(response.data);
            } catch (error) {
                console.error("Error fetching tags:", error);
                setTags([]);
            }
        };

        fetchTags();


        const userData = JSON.parse(localStorage.getItem('user'));
        if (userData) {
            setUser(userData);
            fetchProfileId(userData.userid); // Fetch profileId when user data is available
        }
    }, []);// Empty dependency array ensures this effect runs once after initial load



    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevData => ({
            ...prevData,
            [name]: value
        }));
    };

      // Handler for tag checkbox changes
      const handleTagChange = (e) => {
        const value = Number(e.target.value);  // Extract the tag ID
        const checked = e.target.checked;  // Determine if checkbox is checked

        setFormData(prevFormData => {
            const updatedTagIds = checked
                ? [...prevFormData.tagIds, value]  // Add tag ID 
                : prevFormData.tagIds.filter(tagId => tagId !== value);  // Remove tag ID 

            return {
                ...prevFormData,
                tagIds: updatedTagIds  // Update tagIds in formData
            };
        });
    };

    // Handler for file input changes
    const handleFileChange = (e) => {
        const selectedFile = e.target.files[0];  // Extract the selected file
        if (selectedFile) {
            setSelectedFile(selectedFile);  // Update state with selected file
            setPreview(URL.createObjectURL(selectedFile));  //  preview URL
            setError("");  
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!selectedFile) {
            setError("Please select a file before uploading.");
            return;
        }

        setUploading(true);

        const formDataToSend = new FormData();

        formDataToSend.append('title', formData.title);
        formDataToSend.append('description', formData.description);
        formDataToSend.append('price', formData.price);
        
        formDataToSend.append('tagIds', formData.tagIds.join(','));
        formDataToSend.append('image', selectedFile);
        formDataToSend.append('profileId', profileId); 

        // for debugging
        formDataToSend.forEach((value, key) => {
        console.log(`${key}: ${value}`);
        });

        try {
            await api.uploadArtwork(formDataToSend);
            alert('Artwork uploaded successfully!');
            setFormData({
                title: '',
                description: '',
                price: '',
                tagIds: []
            });
            alert("File uploaded successfully");
            setSelectedFile(null);
            setPreview("");
            if (onUploadSuccess && typeof onUploadSuccess === 'function') {
                onUploadSuccess();
            }
            
            //navigate('/artworkslist'); 
        } catch (error) {
            setError('Failed to upload artwork');
            console.error("Error uploading artwork:", error);
        }finally {
            setUploading(false);
        }
    };

    return (
      <div className="app-container">
          <Banner />
          <div className="upload-artwork-container">
            <h1>Upload New Artwork</h1>
            <form onSubmit={handleSubmit}>
                <label>
                    Title:
                    <input type="text" name="title" value={formData.title} onChange={handleChange} required />
                </label>
                <br />
                <label>
                    Description:
                    <textarea name="description" value={formData.description} onChange={handleChange} required />
                </label>
                <br />
                <label>
                    Price:
                    <input type="number" name="price" value={formData.price} onChange={handleChange} required />
                </label>
                <br />
                <label>
                    Upload Image:
                    <input type="file" name="image" onChange={handleFileChange} />
                </label>
                {preview && <img src={preview} alt="Preview" style={{ marginTop: '10px', width: '200px', height: 'auto' }} />}
                {error && <p style={{ color: 'red' }}>{error}</p>}
                <br />
                <label>
    Tags:
    <div>
        {tags.length > 0 ? (
            tags.map(tag => (
                <div key={tag.tagId} className="checkbox-item">
                    <input
                        type="checkbox"
                        id={`tag-${tag.tagId}`} // Unique id for each checkbox
                        value={tag.tagId}
                        checked={formData.tagIds.includes(tag.tagId)}
                        onChange={handleTagChange}
                    />
                    <label htmlFor={`tag-${tag.tagId}`} className="checkbox-label">
                        {tag.name}
                    </label>
                </div>
            ))
        ) : (
            <p>No tags available</p>
        )}
    </div>
</label>
                <br />
                <button type="submit">Upload Artwork</button>
            </form>

            {error && <p className="error-message">{error}</p>}
        </div>
      </div>    
    );
};

export default UploadArtwork