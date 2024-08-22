import {AiFillLike, AiFillDislike} from 'react-icons/ai';
import { getArtworkById, isArtworkLiked, likeArtwork, unlikeArtwork } from '../../services/artworkService';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';



const Like = ({productId}) => {
    const[isLiked, setIsLiked] = useState(false);
    
    const[message, setMessage] = useState(null);
    
    // const handleClick = () => {
    //     setLiked(!isLiked);
    // };
  

    useEffect(() => {
        const checkIfLiked = async () => {
            try {
                const userData = localStorage.getItem('user');
                const userId = parseInt(JSON.parse(userData).userid); // gets logged in user's id
                //console.log(userId);
                const likeStatus = await isArtworkLiked(userId, productId);
                console.log(likeStatus);
                setIsLiked(likeStatus); // sets the like status
            } catch (error) {
                console.error("Error checking like status", error);
            }
        };

        checkIfLiked();
    }, [productId]);
    
    const handleLike = async (e) => {
        e.preventDefault();
        try {
            const userData = localStorage.getItem('user');
            const userId = JSON.parse(userData).userid; // gets logged in user's id
            
            if (isLiked) {
                await unlikeArtwork(userId, productId);
                setIsLiked(false);
                setMessage("Artwork Unliked.");
            } else {
                await likeArtwork(userId, productId);
                setIsLiked(true);
                setMessage("Artwork is Liked!");
            }
        } catch (error) {
            setMessage("Unable to update like status.");
        }
    }
// if(isLiked)
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
        
            <div className="submit-container">
                <button type="submit" className="submit" onClick={handleLike}>
                    {isLiked ? "UnLike" : "Like"} 
                </button>
            </div>
       
    </div>
    )

};

export default Like;
