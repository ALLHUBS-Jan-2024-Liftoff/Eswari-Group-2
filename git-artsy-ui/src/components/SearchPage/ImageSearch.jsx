import React, { useState } from 'react';
import axios from 'axios';
import 

// artworksSearch component handles the search functionality for artworkss
const artworksSearch = () => {
  // State variables for keyword and artworkss
  const [keyword, setKeyword] = useState('');
  const [artworks, setArtworks] = useState([]);

  // Handles the search form submission
  const handleSearch = async (e) => {
    e.preventDefault();
    try {
      // Fetch artworkss from the backend API based on the keyword
      const response = await axios.get(`http://localhost:8082/api/artworks/search?keyword=${tag}`);
      // Update the artworkss state with the fetched data
      setArtworks(response.data);
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
          placeholder="Search for artworks..."
        />
        <button type="submit">Search</button>
      </form>
      <div className="artworks-list">
        {artworks.map((artwork, index) => (
          <img key={artwork.fileDownloadUri} alt= {artwork.title} />
        ))}
      </div>
    </div>
  );
};

export default artworksSearch;