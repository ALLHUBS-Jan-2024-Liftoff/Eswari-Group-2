import axios from 'axios';

const BASE_URL = 'http://localhost:8082/gitartsy/api/like'; 

export const isArtworkLiked = async (userId, artworkId) => {
    try {
        const response = await axios.get(`${BASE_URL}/artworks/${artworkId}/like-status`, {
            params: { userId }
        });
        return response.data.liked;
    } catch (error) {
        console.error("Error checking if artwork is liked", error);
        throw error;
    }
};

export const likeArtwork = async (userId, artworkId) => {
    try {
        await axios.post(`${BASE_URL}/artworks/${artworkId}/like`, { userId });
    } catch (error) {
        console.error("Error liking artwork", error);
        throw error;
    }
};

export const unlikeArtwork = async (userId, artworkId) => {
    try {
        await axios.delete(`${BASE_URL}/artworks/${artworkId}/unlike`, { data: { userId } });
    } catch (error) {
        console.error("Error unliking artwork", error);
        throw error;
    }
};

// Optional: If you want to import all these functions together
export default {
    isArtworkLiked,
    likeArtwork,
    unlikeArtwork
};
