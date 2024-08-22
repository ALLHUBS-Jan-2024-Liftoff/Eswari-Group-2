import { AiFillLike, AiFillDislike } from 'react-icons/ai';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { isArtworkLiked, likeArtwork, unlikeArtwork } from '../../services/LikeService';


const Like = () => { 
    const [liked, setLiked] = useState(false);
    const [message, setMessage] = useState(null);
    
    const { Id } = useParams(); 

    useEffect(() => {
        const checkIfLiked = async () => {
            try {
                const userData = localStorage.getItem('user');
                const userId = JSON.parse(userData).userId; // Get logged-in user's ID

                const likeStatus = await isArtworkLiked(userId, Id);
                setLiked(likeStatus); // Set the like status
            } catch (error) {
                console.error("Error checking like status", error);
            }
        };

        checkIfLiked();
    }, [Id]);

    const handleLike = async (e) => {
        e.preventDefault();
        try {
            const userData = localStorage.getItem('user');
            const userId = JSON.parse(userData).userId; // Get logged-in user's ID

            if (like) {
                await unlikeArtwork(userId, Id);
                setLiked(false);
                setMessage("Artwork Unliked.");
            } else {
                await likeArtwork(userId, Id);
                setLiked(true);
                setMessage("Artwork is Liked!");
            }
        } catch (error) {
            setMessage("Unable to update like status.");
            console.error("Error updating like status", error);
        }
    };

    return (
        <div>
            <button onClick={handleLike}>
                {liked ? "Unlike" : "Like"}
            </button>
        </div>
    );
};

export default Like; 
