import React, { useState, useEffect } from 'react';
import axios from 'axios';
import TagService from '../../services/TagService';
import artworkService from '../../services/artworkService';
import './SearchPage.css';



const ArtworksSearch = () => {
  const [title, setTitle] = useState('');
  const [selectedTags, setSelectedTags] = useState([]);
  const [tags, setTags] = useState([]);
  const [artworks, setArtworks] = useState([]);

  useEffect(() => {
    TagService.getAllTags().then(data => setTags(data))
                           .catch(error => console.error("Error loading tags:", error));
  }, []);

// Handle form submission for searching artworks
const handleSearch = async (e) => {
  e.preventDefault();
  try {
    const { profileId } = useParams();
    const response = await artworkService.fetchArtworksByProfile(profileId);
    // Filter fetched artworks based on title and tags
    const filteredArtworks = artworks.filter(artwork => 
      artwork.title.toLowerCase().includes(title.toLowerCase()) &&
      (selectedTags.length === 0 || artwork.tags.some(tag => selectedTags.includes(tag.id)))
    );

    // Update artworks state with the filtered results
    setArtworks(filteredArtworks);
  } catch (error) {
    console.error('Error fetching artworks:', error);
  }
};


  return (
    <div className="artworks-search">
      <h1>Search Artworks</h1>
      <form onSubmit={handleSearch} className="search-form">
        <input
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Search by title or tag"
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
      <div className="artworks-list">
        {artworks.map((artwork, index) => (
          <img key={index} src={artwork.imageUrl} alt={artwork.title} />
        ))}
      </div>
    </div>
  );
};

export default ArtworksSearch;
