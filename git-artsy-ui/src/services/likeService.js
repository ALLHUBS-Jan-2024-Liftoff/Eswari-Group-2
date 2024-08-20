import axios from 'axios';

const BASE_URL = 'http://localhost:8082/gitartsy/api/likes';

//API methods for like operations

const getAllLikes = async () => {
    try{ 
        const response = await axios.get(BASE_URL);
        console.log(response.data)
        return response.data
    } catch (error) {
        console.error('Error fetching likes:', error);
        throw error;
    }
        
    };


const getLikeById = (id) => {
    return axios.get(`${BASE_URL}/${id}`)
    .catch(error => {
        console.error(`Error fetching like with ID ${id} :`, error);
        throw error;
    } );
};

const createLike = (likeId, count) => {
    return axios.post(BASE_URL)
    .catch(error => {
        console.error(`Error creating likes`, error);
        throw error;
    });
};

const updateLike = (id, likeData) => {
    return axios.put(`${BASE_URL}/${id}`, likeData)
    .catch(error => {
        console.error(`Error with updating count with like ID ${id}`, error);
        throw error;
    });
};

export default {
    getAllLikes,
    getLikeById,
    createLike,
    updateLike
};