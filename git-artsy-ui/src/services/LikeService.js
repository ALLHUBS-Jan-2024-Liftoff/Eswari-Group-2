import axios from 'axios';

const BASE_URL = 'http://localhost:8082/gitartsy/api/like';

// Function to check if the artwork is liked by the user
export const isArtworkLiked = async (userId, artworkId) => {
    if (!userId || !artworkId) {
        console.error("User ID or Artwork ID is missing");
        return null;
    }

    try {
        const response = await axios.get(`${BASE_URL}/artworks/${artworkId}/like-status`, {
            params: { userId }
        });
        return response.data;  // Return only the data
    } catch (error) {
        console.error("Error checking if artwork is liked", error);
        throw error;
    }
};

// Function to like an artwork
export const likeArtwork = async (userId, artworkId) => {
    if (!userId || !artworkId) {
        console.error("User ID or Artwork ID is missing");
        return;
    }

    try {
        await axios.post(`${BASE_URL}/artworks/${artworkId}/like`, { userId });
    } catch (error) {
        console.error("Error liking artwork", error);
        throw error;
    }
};

// Function to unlike an artwork
export const unlikeArtwork = async (userId, artworkId) => {
    if (!userId || !artworkId) {
        console.error("User ID or Artwork ID is missing");
        return;
    }

    try {
        await axios.delete(`${BASE_URL}/artworks/${artworkId}/unlike`, { data: { userId } });
    } catch (error) {
        console.error("Error unliking artwork", error);
        throw error;
    }
};
