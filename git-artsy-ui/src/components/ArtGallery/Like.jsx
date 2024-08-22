import {AiFillLike, AiFillDislike} from 'react-icons/ai';
import artworkService, { isArtworkLiked } from '../../services/artworkService';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';



const like = () => {
    const[liked, setLiked] = useState(false);
    const[message, setMessage] = useState(null);
    
    // const handleClick = () => {
    //     setLiked(!liked);
    // };
    const { artworkId } = useParams();

    useEffect(() => {
        const checkIfLiked = async () => {
            try {
                const userData = localStorage.getItem('user');
                const userId = JSON.parse(userData).userId; // gets logged in user's id

                const likeStatus = await isArtworkLiked(userId, artworkId);
                setLiked[likeStatus]; // sets the like status
            } catch (error) {
                console.error("Error checking like status", error);
            }
        };

        checkIfLiked();
    }, [artworkId]);
    
    const handleLike = async (e) => {
        e.preventDefault();
        try {
            const userData = localStorage.getItem('user');
            const userId = JSON.parse(userData).userId; // gets logged in user's id
            
            if (isArtworkLiked) {
                await unlikeArtwork(userId, artworkId);
                setLiked(false);
                setMessage("Artwork Unliked.");
            } else {
                await likeArtwork(userId, artworkId);
                setLiked(true);
                setMessage("Artwork is Liked!");
            }
        } catch (error) {
            setMessage("Unable to update follow status.");
        }
    }
// if(liked)
//     return (<AiFillLike
//               color="blue" 
//               size="50" 
//               onClick={handleLike}/>)
//           return (<AiFillDislike
//             color="red" 
//             size="50" 
//             onClick={handleLike}/>)  
    return(
        <div>
            <button onClick={handleLike}>{isArtworkLiked ? "Unlike" : "Like"}</button>
        </div>
    )

};

export default like;
