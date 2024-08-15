import React, { useState } from 'react';
import axios from 'axios';
import AddTag from './AddTag';
import './artworksSearch.css';

// artworksItem component represents a single artworks and its associated tags
const artworksItem = ({ artworks }) => {
  // State variable for tags
  const [tags, setTags] = useState(artworks.tags || []);

  // Function to add a tag to the artworks
  const addTag = async (tagName) => {
    try {
      // Send a request to the backend to add the tag to the artworks
      const response = await axios.post(`/api/artworkss/${artworks.id}/tags?tagName=${tagName}`);
      // Update the tags state with the updated tag list
      setTags(response.data.tags);
    } catch (error) {
      console.error('Error adding tag:', error);
    }
  };

  return (
    <div className="artworks-item">
      <img src={artworksSearchsartworksURl} alt={artworksSearch.title} />
      <div>{artworks.title}</div>
      <div>{artworks.description}</div>
      <div className="tags">
        {tags.map((tag) => (
          <span key={tag.id} className="tag">{tag.name}</span>
        ))}
      </div>
      <AddTag addTag={addTag} />
    </div>
  );
};

export default artworksItem;