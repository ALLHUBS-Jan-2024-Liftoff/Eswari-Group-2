import React, { useState, useEffect } from 'react';
import TagService from '../../services/TagService';
import ArtworksSearchService from '../../services/ArtworkSearchService'; 
import '../SearchPage/SearchPage.css';

const ArtworksSearch = () => {
  const [title, setTitle] = useState('');
  const [tags, setTags] = useState([]);
  const [selectedTags, setSelectedTags] = useState([]);
  const [artworks, setArtworks] = useState([]);
  const [error, setError] = useState('');
  const [searchPerformed, setSearchPerformed] = useState(false);

  // useEffect hook to fetch tags
  useEffect(() => {
    const fetchTags = async () => {
      try {
        const response = await TagService.getAllTags();
        console.log(response);
        setTags(response || []);
      } catch (error) {
        console.error("Error fetching tags:", error);
        setTags([]);
      }
    };

    fetchTags();
  }, []); // Empty dependency array ensures this effect runs once after initial load

  const handleSearch = async (e) => {
    e.preventDefault();
    setSearchPerformed(true);
    try {
      // Filter selected tags to get their names
      const selectedTagNames = tags
        .filter(tag => selectedTags.includes(tag.tagId))
        .map(tag => tag.name);
  
      const query = {
        title,
        tags: selectedTagNames, // Pass tag names instead of IDs
      };
      const response = await ArtworksSearchService.searchArtworks(query);
      console.log('Artworks response:', response);
  
      if (Array.isArray(response)) {
        setArtworks(response);
      } else {
        console.error('Expected an array of artworks, but got:', response);
        setArtworks([]); // Ensure artworks is always an array
      }
    } catch (error) {
      setError('Error fetching artworks');
      console.error('Error fetching artworks:', error);
      setArtworks([]); // Ensure artworks is always an array
    }
  };
  
  const handleTagClick = (tagId) => {
    setSelectedTags(prevSelectedTags => 
      prevSelectedTags.includes(tagId)
        ? prevSelectedTags.filter(id => id !== tagId)
        : [...prevSelectedTags, tagId]
    );
  };
  

  return (
    <div className="app-container">
      <div className="artworks-search">
        <h1 style={{ textAlign: 'center' }}>Search Artworks</h1>
        <form onSubmit={handleSearch} className="search-form">
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="Search by Title or Description"
            className="search-input"
          />
          <div className="tag-list">
            {tags.length > 0 ? (
              <ul>
                {tags.map((tag) => (
                  <li 
                    key={tag.tagId} 
                    className={`tag-item ${selectedTags.includes(tag.tagId) ? 'selected' : ''}`}
                    onClick={() => handleTagClick(tag.tagId)}
                  >
                    {tag.name}
                  </li>
                ))}
              </ul>
            ) : (
              <p>No tags available</p>
            )}
          </div>
          <button type="submit" className="search-button">Search</button>
        </form>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        {searchPerformed && artworks.length === 0 && (
          <p>No artworks found</p>
        )}
        {artworks.length > 0 && (
          <div className="artwork-list">
            {artworks.map((artwork, index) => (
              <div className="artwork-item" key={`${artwork.fileDownloadUri}-${index}`}>
                <img src={artwork.fileDownloadUri} alt={artwork.title} className="artwork-image" />
                <div className="artwork-details">
                  <h3 className="artwork-title">{artwork.title}</h3>
                  <p className="artwork-description">{artwork.description}</p>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default ArtworksSearch;
