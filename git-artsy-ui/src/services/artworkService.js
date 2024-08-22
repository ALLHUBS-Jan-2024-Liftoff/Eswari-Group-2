import axios from 'axios';



const BASE_URL = 'http://localhost:8082/gitartsy/api/artworks';

// Add a new artwork
export const uploadArtwork =   (formData) => {
  try {
    const response =   axios.post(`${BASE_URL}/new`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'        
      },
      withCredentials: true, 
    });
    return response.data;
  } catch (error) {
   
    throw new Error("There was an error creating the artwork!");
  }
};


// Fetch artworks by profile ID
export const fetchArtworksByProfile = async (profileId) => {
  try {
    const response = await axios.get(`${BASE_URL}/profile/${profileId}`, {
      withCredentials: true, // Ensure cookies are sent with the request
    });
    console.log(response);
    return response.data;
  } catch (error) {
    console.error("There was an error fetching the artworks!", error);
    throw error;
  }
};

//get all artwork
const getAllArtwork = async () => {
  return axios.get(BASE_URL)
      .catch(error => {
          console.error("Error fetching the artwork.", error);
          throw error;
      });
};



//getByID method 

export const getArtworkById = async (Id) => {
  try {
    const response = await axios.get(`${BASE_URL}/singleartworksdetails/${Id}`);
    return response;
  } catch {
    console.error("Artwork Id not found", error);
    throw error;
  }
}

//liked methods

export const isArtworkLiked = async (userId, artworkId) => {
  try {
    const response = await axios.get('http://localhost:8082/api/likes/status', {
      params: { userId, artworkId},
    });
    console.log(response.data);
    return response.data.isLiked;
  } catch (error)
{
  console.error("Error checking the liked status", error);
  throw error;
}};

export const likeArtwork = async (userId, artworkId) => {
  try {
    const response = await axios.post('http://localhost:8082/api/likes/like', null, {
      params: { userId, artworkId},
    });
    console.log(response)
    return response;
  } catch (error)
{
  console.error("Error in liking artwork", error);
  throw error;
}};

export const unlikeArtwork = async (userId, artworkId) => {
  try {
    const response = await axios.post('http://localhost:8082/api/likes/unlike', null, {
      params: { userId, artworkId},
    });
    return response;
  } catch (error)
{
  console.error("Error in unliking artwork", error);
  throw error;
}};

// Delete an artwork by its ID
export const deleteArtwork = async (artworkId) => {
  try {
    await axios.delete(`${BASE_URL}/deleteartwork/${artworkId}`, {
      withCredentials: true,
    });
  } catch (error) {
    console.error("There was an error deleting the artwork!", error);
    throw error;
  }
};


export default {
  
  uploadArtwork,
  fetchArtworksByProfile,
  getAllArtwork,

  getArtworkById,
  isArtworkLiked,
  likeArtwork,
  unlikeArtwork

  deleteArtwork

};