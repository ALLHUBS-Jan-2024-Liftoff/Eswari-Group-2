import React, { useState, useEffect} from "react";
import Button from "react-bootstrap/Button";
//import api from '../services/likeService';
import axios from "axios";


const likeButton = () => {
    //state

    const [count, setCount] = useState(0);
    
    //fetch Likes from server
    useEffect(() => {
       axios.get('http://localhost:8082/gitartsy/api/likes').then(response => setCount(response.data.count))
        .catch(error => console.error('Error fetching like count:', error))}, []);
        
    const handleChange = async (e) => {
        e.preventDefault();
        axios.put('http://localhost:8082/gitartsy/api/likes').then(response => setCount(response.data.count + 1))
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