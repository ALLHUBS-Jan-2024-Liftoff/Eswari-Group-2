
import React, { useEffect, useState } from 'react';
import api from '../../services/artworkService';
import LikeButton from '../LikeButton.jsx';
import axios from 'axios';

//TODO ADD PROFILE LINK
const BASE_URL = 'http://localhost:8082/uploads/';

//fetch all user artworks

const UserArt = () => {
    
        const [artworks, setArtworks] = useState([]);
        const [error, setError] = useState("");
        
        useEffect(() => {
            const getAllArts = async () => {
                try {
                        const response = await api.getAllArtwork();
                        console.log("Response from API:", response.data);
                        if (response) {
                            setArtworks(response.data);
                        } else {
                            throw new Error("Invalid response structure");
                        }
                    } catch (error) {
                        setError("Failed to fetch artworks");
                        console.error("Error fetching artworks:", error);
                    }
                };
            
            getAllArts();
        }, []);;
    
        

        return (
            <div>
            <ul>
                {artworks.length > 0 ? (
                    artworks.map((artwork, index) => (
                        <li className="row" key={`${artwork.fileDownloadUri}-${index}`}>
                            
                            {artwork.fileDownloadUri && (
                                <img className='col-md-6' src={artwork.fileDownloadUri} alt={artwork.title} />
                            )}
                           <h3 className='col-md-4 text-center'>{artwork.title}</h3>
                           < LikeButton />
                        </li>
                    ))
                ) : (
                    <p>No artworks available</p>
                )}
            </ul>
        </div>

    )}
export default UserArt

