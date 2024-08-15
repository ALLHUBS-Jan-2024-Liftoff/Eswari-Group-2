import React, { useState } from 'react';
import axios from 'axios';
import './artworksSearch.css';
import artworksItem from './artworksItem';


// artworksSearch component handles the search functionality for artworkss
const artworksSearch = () => {
  // State variables for keyword and artworkss
  const [keyword, setKeyword] = useState('');
  const [artworks, setartworks] = useState([]);

  // Handles the search form submission
  const handleSearch = async (e) => {
    e.preventDefault();
    try {
      // Fetch artworkss from the backend API based on the keyword
      const response = await axios.get(`/api/artworks/search?keyword=${keyword}`);
      // Update the artworkss state with the fetched data
      setartworkss(response.data);
    } catch (error) {
      console.error('Error fetching artworks:', error);
    }
  };

  return (
    <div className="artworks-search">
      <form onSubmit={handleSearch}>
        <input
          type="text"
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
          placeholder="Search for artworkss..."
        />
        <button type="submit">Search</button>
      </form>
      <div className="artworks-list">
        {artworkss.map((artworks) => (
          <artworksItem key={artworks.id} artworks={artworks} />
        ))}
      </div>
    </div>
  );
};

export default artworksSearch;