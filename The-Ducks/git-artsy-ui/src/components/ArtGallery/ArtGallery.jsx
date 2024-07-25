import React, { useState, useEffect } from 'react';
import axios from 'axios';

//generating a random number from API pages

// const randomNumber = Math.floor(Math.random()*10500);
// const artworkArray= [];


//fetching artwork with random number from API

// const fetchArtworks = async () => {
//     try {
//         const response = await axios.get('https://api.artic.edu/api/v1/artworks?page={randomNumber}&limit=1');
//         const data = response.data.data;
//         const newArtworks = data.map(artwork => ({
//           id: artwork.id,
//           imageUrl: `https://www.artic.edu/iiif/2/${artwork.image_id}/full/843,/0/default.jpg`
//         }));
//         return newArtworks;
//       } catch (error) {
//         console.error('Error fetching artworks:', error);
//       }
//     };

    //loop through fetch artworks 10 times
    //do..while

    // const addArtwork = () => {
    //     do {
    //         artworkArray.push(fetchArtworks(newArtworks.imageUrl));
    //     } while (artworkArray < 10)
    //         return artworkArray;
    //}


    //return artworks for each div 
    
//Try again, different resource 

//adding api without axios? 

//const API_URL = 'https://api.artic.edu/api/v1/artworks';

async function fetchArtworks() {
    try {
        const response = await axios.get('https://api.artic.edu/api/v1/artworks');
                const data = await response.json();
                return data.data;
    } catch (error) {
        console.error('Error fetching artwork data:', error);
        return [];
    }
}

//displaying random artwork 
async function displayRandomArtwork() {
    const artworks = await fetchArtworks();
    if (artworks.length === 0) {
        console.error('No artwork data available');
        return;
    }

    const randomIndex = Math.floor(Math.random() * artworks.length);
    const artwork = artworks[randomIndex];
    const imageUrl = `https://www.artic.edu/iiif/2/${artwork.id}/full/843,/0/default.jpg`;
    const imgElement = document.getElementById('artwork');
    imgElement.src = imageUrl;
    imgElement.alt = artwork.title || 'Artwork';
}

window.onload = displayRandomArtwork;

const ArtGallery = () => {
    return(
        <div class='artwork-container'>
            <img id='artwork' src='{imageURL}'></img>
        </div>
    )
};
export default ArtGallery