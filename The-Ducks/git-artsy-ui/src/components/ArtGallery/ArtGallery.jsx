import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ArtGallery= () => {
    const [artworks, setArtworks] = useState([]);
    const [currentIndex, setCurrentIndex] = useState(0);
  
    useEffect(() => {
      fetchArtworks(); 
    }, []);
  
  
  //Fetching Artworks from external API and limiting to 20 images
    const fetchArtworks = async () => {
      try {
        const response = await axios.get('https://api.artic.edu/api/v1/artworks/search?query[term][is_public_domain]=true&fields=id,title,image_id');
        const data = response.data.data;
        const newArtworks = data.map(artwork => ({
          id: artwork.id,
          imageUrl: `https://www.artic.edu/iiif/2/${artwork.image_id}/full/843,/0/default.jpg`
        }));
        const shuffledArtworks = newArtworks.sort(() => Math.random() * artworks.length);
        setArtworks(shuffledArtworks);
      } catch (error) {
        console.error('Error fetching artworks:', error);
      }
    };

    
      
  
    // async function displayRandomArtwork() {
    //     const artworks = await fetchArtworks();
    //     if (artworks.length === 0) {
    //         console.error('No artwork data available');
    //         return;
    //     }
    
    //     const randomIndex = Math.floor(Math.random() * artworks.length);
    //     const artwork = artworks[randomIndex];
    //     const imageUrl = `https://www.artic.edu/iiif/2/${artworks}/full/843,/0/default.jpg`;
    //     const imgElement = document.getElementById('artwork');
    //     imgElement.src = imageUrl;
    //     imgElement.alt = artwork.title || 'Artwork';
    // }

    // useEffect(() => {

    // })
   

    const  artworkToShow = artworks[currentIndex + Math.floor(Math.random() * artworks.length)];
  
  
    return (
      <>
        {artworkToShow && (
          <div className="gallery">
            <img src={artworkToShow.imageUrl} />
            <img src={artworkToShow.imageUrl} />
          </div>
        )}
      </>
    );
  };


export default ArtGallery