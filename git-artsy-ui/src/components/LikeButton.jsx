import React, { useState, useEffect} from "react";
import Button from "react-bootstrap/Button";
//import api from '../services/likeService';
import axios from "axios";


const likeButton = () => {

    //state
    const [likes, setLikes] = useState();
    const [artworkId, setArtworkId] = useState();
    const [count, setCount] = useState(0);
    
    //fetch Likes from server
    useEffect(() => {
        const fetchLikes = async () => {
            try {
                const response = await axios.get('http://localhost:8082/gitartsy/api/likes')
                console.log(response.data);
                setLikes(response.data);
            } catch (error) {
                console.error('Error fetching likes:', error);
            }
        };

        fetchLikes();
    }, []);
        
    const handleChange = async (count) => {
        axios.get('http://localhost:8082/gitartsy/api/likes').then(response => setCount(response.data.count + 1))
            .catch(error => console.error('Error in adding like', error))
         };
    //


    return(
        <>
        <Button onClick={handleChange}>Like</Button>
        <p>{count}</p>
        </>
    )
}

export default likeButton;