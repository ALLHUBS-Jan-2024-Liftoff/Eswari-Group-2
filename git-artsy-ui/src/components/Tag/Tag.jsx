//By STACI
import Banner from "../Banner";

import React, { useState, useEffect } from 'react';
import api from '../services/TagService';
import '../Tag/Tag.css'; 

const TagPage = () => {
    // State variables
    // State to hold the list of tags
    const [tags, setTags] = useState([]);
    // State for form data (tag name)
    const [formData, setFormData] = useState({ name: '' });
     // State for the currently edited tag ID
    const [currentTagId, setCurrentTagId] = useState(null);
    // State to store error messages
    const [errorMessage, setErrorMessage] = useState('');

    // // useEffect hook to fetch tags from the server
    useEffect(() => {
        const fetchTags = async () => {
            try {
                const response = await api.getAllTags();
                console.log(response.data);
                setTags(response.data);
            } catch (error) {
                console.error('Error fetching tags:', error);
            }
        };

        fetchTags();
    }, []);//this effect runs once


    // useEffect(() => {
    //     const fetchTags = async () => {
    //         try {
    //             const response = await api.getAllTags();
                
    //             const tagsData = Array.isArray(response.data) ? response.data : [];
    //             console.log(tagsData);
    //             setTags(tagsData);
    //         } catch (error) {
    //             console.error('Error fetching tags:', error);
    //             setTags([]); // Set an empty array on error
    //         }
    //     };

    //     fetchTags();
    // }, []);
        // Handler for input changes in the form
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            if (currentTagId) {
                await api.updateTag(currentTagId, formData);
                alert('Tag updated successfully!');
            } else {
                await api.createTag(formData);
                alert('Tag created successfully!');
            }
            setFormData({ name: '' });
            setCurrentTagId(null);
            const response = await api.getAllTags();
            setTags(response.data);
        } catch (error) {
            setErrorMessage('Failed to save tag');
            console.error('Error saving tag:', error);
        }
    };

     // Handler for editing a tag
    const handleEdit = (tag) => {
        setFormData({ name: tag.name });
        setCurrentTagId(tag.tagId);
    };

       // Handler for deleting a tag
    const handleDelete = async (id) => {
        try {
            await api.deleteTag(id);
            setTags(tags.filter(tag => tag.tagId !== id));
        } catch (error) {
            console.error('Error deleting tag:', error);
        }
    };
    return (
      <div className="app-container">
          <Banner />
          <div className="tag-manager-container">
            <h1>Tag Manager</h1>

            <form onSubmit={handleSubmit}>
                <label>
                    Name:
                    <input
                        type="text"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                </label>
                <br />
                <button type="submit">
                    {currentTagId ? 'Update Tag' : 'Create Tag'}
                </button>
                {errorMessage && <p className="error-message">{errorMessage}</p>}
            </form>

            <h2>All Tags</h2>
            <ul>
                {tags.map(tag => (
                    <li key={tag.tagId}>
                        {tag.name}
                        <div>
                            <button className="btn-edit" onClick={() => handleEdit(tag)}>Edit</button>
                            <button className="btn-delete" onClick={() => handleDelete(tag.tagId)}>Delete</button>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
      </div>    
    );
};

export default TagPage