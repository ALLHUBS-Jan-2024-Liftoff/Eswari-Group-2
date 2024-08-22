import {AiFillLike, AiFillDislike} from 'react-icons/ai';
import { useState } from 'react';



const like = () => {
    const[liked, setLiked] = useState(false);
    const handleClick = () => {
        setLiked(!liked);
    };
    const { artworkId } = useParams();

    useEffect(() => {
        const checkIfLiked = async () => {
            try {
                const userData = localStorage.getItem('user');
                const userId = JSON.parse(userData).userid; // gets logged in user's id

                const likeStatus = await isUserFollowed(userId, profileId);
                setIsFollowing(followingStatus); // sets the following status
            } catch (error) {
                console.error("Error checking following status", error);
            }
        };
    
if(liked)
    return (<AiFillLike
              color="blue" 
              size="50" 
              onClick={handleClick}/>)
          return (<AiFillDislike
            color="red" 
            size="50" 
            onClick={handleClick}/>)  
}

export default like;
