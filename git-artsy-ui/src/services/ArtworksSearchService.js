import axios from 'axios';

const BASE_URL = 'http://localhost:8082/gitartsy/api';

// Search artworks by title or tags
export const searchArtworks = async (query) => {
  try {
    const response = await axios.get(`${BASE_URL}/search`, {
      params: query,
      withCredentials: true,
    });
    return response.data;
  } catch (error) {
    console.error("There was an error searching the artworks!", error);
    throw error;
  }
};

export default {
  searchArtworks,
};
