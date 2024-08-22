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

// export const getArtworkById = async () => {
//   return axios.get(`${BASE_URL}/${id}`);
  
// }

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

export const isArtworkLiked = async (userId, Id) => {
  try {
    const response = await axios.get('http://localhost:8082/gitartsy/api/like/status', {
      params: { userId, Id},
    });
    return response.data;
  } catch (error)
{
  console.error("Error checking the liked status", error);
  throw error;
}};

export const likeArtwork = async (userId, Id) => {
  try {
    const response = await axios.post('http://localhost:8082/gitartsy/api/like', null, {
      params: { userId, Id},
    });
    console.log(response)
    return response;
  } catch (error)
{
  console.error("Error in liking artwork", error);
  throw error;
}};

export const unlikeArtwork = async (userId, Id) => {
  try {
    const response = await axios.post('http://localhost:8082/gitartsy/api/like/unlike', null, {
      params: { userId, Id},
    });
    return response;
  } catch (error)
{
  console.error("Error in unliking artwork", error);
  throw error;
}};

export default {
  
  uploadArtwork,
  fetchArtworksByProfile,
  getAllArtwork
};