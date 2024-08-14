
import React, { useEffect, useState } from 'react';
import api from '../../services/artworkService';
import axios from 'axios';


const BASE_URL = 'http://localhost:8082/gitartsy/api/artworks';

//fetch all user artworks

const UserArt = () => {
    
        const [artworks, setArtworks] = useState([]);
        const [error, setError] = useState("");
        
        useEffect(() => {
            const getAllArts = async () => {
                try {
                        const response = await api.getAllArtwork;
                        console.log("Response from API:", response);
                        if (response) {
                            setArtworks(response);
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
    
        

        return (<ul>
            {artworks.length > 0 ? (
                artworks.map((artwork, index) => (
                    <li key={`${artwork.fileDownloadUri}-${index}`}>
                        <h2>{artwork.title}</h2>
                        {artwork.fileDownloadUri && (
                            <img src={artwork.fileDownloadUri} alt={artwork.title} style={{ width: '200px', height: 'auto' }} />
                        )}
                       
                    </li>
                ))
            ) : (
                <p>No artworks available</p>
            )}
        </ul>
            
        )

    }
export default UserArt

