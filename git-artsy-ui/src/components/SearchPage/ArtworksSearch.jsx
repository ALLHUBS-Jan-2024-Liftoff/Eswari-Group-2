import React, { useState, useEffect } from 'react';
import TagService from '../../services/TagService';
import ArtworksSearchService from '../../services/ArtworksSearchService'; 
import './SearchPage.css';

const ArtworksSearch = () => {
  const [title, setTitle] = useState('');
  const [selectedTags, setSelectedTags] = useState([]);
  const [tags, setTags] = useState([]);
  const [artworks, setArtworks] = useState([]); // Initialize as an empty array
  const [error, setError] = useState("");
  const [searchPerformed, setSearchPerformed] = useState(false); // Track if a search has been performed

  useEffect(() => {
    TagService.getAllTags()
      .then(data => setTags(data))
      .catch(error => console.error("Error loading tags:", error));
  }, []);

  const handleSearch = async (e) => {
    e.preventDefault();
    setSearchPerformed(true); // Mark that a search has been performed
    try {
      const query = { title, tags: selectedTags };
      const response = await ArtworksSearchService.searchArtworks(query);
      setArtworks(Array.isArray(response) ? response : []); // Ensure response is an array
      setError(""); // Clear any previous errors on successful search
    } catch (error) {
      setArtworks([]); // Clear artworks if there's an error
      setError('Error fetching artworks');
      console.error('Error fetching artworks:', error);
    }
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
            placeholder="Search by Title or Tag"
            className="search-input"
          />
          <select
            multiple
            value={selectedTags}
            onChange={e => setSelectedTags([...e.target.selectedOptions].map(o => o.value))}
            className="tag-select"
          >
            {tags.map(tag => (
              <option key={tag.id} value={tag.id}>{tag.name}</option>
            ))}
          </select>
          <button type="submit" className="search-button">Search</button>
        </form>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        {searchPerformed && artworks.length === 0 && !error && (
          <p>No artworks available</p> // Show only after a search is performed without error
        )}
        {Array.isArray(artworks) && artworks.length > 0 && (
          <ul>
            {artworks.map((artwork, index) => (
              <li className="row" key={`${artwork.fileDownloadUri}-${index}`}>
                {artwork.fileDownloadUri && (
                  <img className="col-md-6" src={artwork.fileDownloadUri} alt={artwork.title} />
                )}
                <h3 className="col-md-4 text-center">{artwork.title}</h3>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default ArtworksSearch;
