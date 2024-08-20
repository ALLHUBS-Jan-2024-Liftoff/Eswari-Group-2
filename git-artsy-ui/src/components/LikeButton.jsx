import React, { useState, useEffect} from "react";
import Button from "react-bootstrap/Button"


const likeButton = () => {
    //state
    const [like, setLike] = useState();
    const [currentLike, setCurrentLike] = useState(null);
    const [count, setCount] = useState(0);
    
    //fetch Likes from server
    useEffect(() => {
        const fetchLikes = async () => {
            try {
                const response = await api.getAllLikes();
                console.log(response);
                setLike(response);
            } catch (error) {
                console.error('Error in fetching likes')
            }
        };
        fetchLikes();
    }, []); //runs once


    const handleChange = async (e) => {
        e.preventDefault();
        try {
            if (currentLike) {
                await api.updateLike(currentLike, count);

            } else {
                await api.createLike(count);
                alert('Liked!');
            }
            setCount(count + 1); 
        } catch (error) {
            console.error('Error in liking artwork');
        }
        

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