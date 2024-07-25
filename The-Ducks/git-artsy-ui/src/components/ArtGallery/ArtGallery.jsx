import React, { useState, useEffect } from 'react';
import axios from 'axios';

//generating a random number from API pages

const randomNumber = Math.floor(Math.random()*10500);
const artworkArray= [];
//fetching artwork with random number from API

const fetchArtworks = async () => {
    try {
        const response = await axios.get('https://api.artic.edu/api/v1/artworks?page={randomNumber}&limit=1');
        const data = response.data.data;
        const newArtworks = data.map(artwork => ({
          id: artwork.id,
          imageUrl: `https://www.artic.edu/iiif/2/${artwork.image_id}/full/843,/0/default.jpg`
        }));
        return newArtworks;
      } catch (error) {
        console.error('Error fetching artworks:', error);
      }
    };

    //loop through fetch artworks 10 times
    //do..while
    const addArtwork = () => {
        do {
            artworkArray.push(fetchArtworks(newArtworks.imageUrl));
        } while (artworkArray < 10)
            return artworkArray;
    }
    //return artworks for each div 
    
    
const ArtGallery = () => {
    return(
        <div class='artwork-display'>
            {artworkArray.imageUrl}
        </div>
    )
};
export default ArtGallery