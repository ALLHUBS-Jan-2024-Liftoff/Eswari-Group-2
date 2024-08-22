import React, { useEffect, useState } from 'react';
import api from "../../services/artworkService";
import Banner from "../Banner";

const ArtworkList = () => {
    const [artworks, setArtworks] = useState([]);
    const [error, setError] = useState("");
    const [user, setUser] = useState(null);
    const [profileId, setProfileId] = useState(null);
    
    const fetchProfileId = async (userId) => {
        try {
            const response = await fetch(`http://localhost:8082/gitartsy/api/profiles/profileid/${userId}`);
            const data = await response.json();
            
            if (data !== 0) {
                setProfileId(data);
            } else {
                console.error('Profile not found for the user');
                setError('Profile not found');
            }
        } catch (error) {
            console.error("Error fetching profile ID:", error);
            setError('Error fetching profile ID');
        }
    };

    useEffect(() => {
        const fetchUserData = () => {
            const userData = JSON.parse(localStorage.getItem('user'));
            if (userData) {
                setUser(userData);
                fetchProfileId(userData.userid);
            } else {
                setError("No user data found");
            }
        };

        fetchUserData();
    }, []);

    useEffect(() => {
        const getAllArts = async () => {
            if (profileId) {
                try {
                    const response = await api.fetchArtworksByProfile(profileId);
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
            }
        };

        getAllArts();
    }, [profileId]);

    const handleDelete = async (artworkId) => {
        console.log("Artwork Id from Delete : " +artworkId)
        try {
            await api.deleteArtwork(artworkId);
            setArtworks(artworks.filter(artwork => artwork.id !== artworkId));
        } catch (error) {
            console.error("Error deleting artwork:", error);
            setError("Failed to delete artwork");
        }
    };

    return (
        <div className="app-container">
            <Banner />
            <h1>Artwork List</h1>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <ul>
                {artworks.length > 0 ? (
                    artworks.map((artwork, index) => (
                        <li key={`${artwork.fileDownloadUri}-${index}`}>
                            <h2>{artwork.title}</h2>
                            {artwork.fileDownloadUri && (
                                <img src={artwork.fileDownloadUri} alt={artwork.title} style={{ width: '200px', height: 'auto' }} />
                            )}
                            <div>
                                <button onClick={() => handleDelete(artwork.id)}>Delete</button>
                            </div>
                        </li>
                    ))
                ) : (
                    <p>No artworks found</p>
                )}
            </ul>
        </div>
    );
};

export default ArtworkList;
