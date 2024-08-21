import React, { useState, useEffect } from 'react';
import { followArtist, unfollowArtist, isUserFollowed } from '../../services/userService';
import { useParams } from 'react-router-dom';

export const Follow = () => {
    const [message, setMessage] = useState(null);
    const [isFollowing, setIsFollowing] = useState(false);

    const { profileId } = useParams(); // Get user id of the profile that we are on

    useEffect(() => {
        const checkIfFollowing = async () => {
            try {
                const userData = localStorage.getItem('user');
                const userId = JSON.parse(userData).userid; // Get the logged-in user's id

                const followingStatus = await isUserFollowed(userId, profileId);
                setIsFollowing(followingStatus); // Set the following status
            } catch (error) {
                console.error("Error checking following status", error);
            }
        };

        checkIfFollowing();
    }, [profileId]);

    const handleFollow = async (e) => {
        e.preventDefault();
        try {
            const userData = localStorage.getItem('user');
            const userId = JSON.parse(userData).userid; // Get the logged-in user's id

            console.log("userId: " + userId);
            console.log("profileId: " + profileId);
            
            if (isFollowing) {
                await unfollowArtist(userId, profileId);
                setIsFollowing(false);
                setMessage("User unfollowed.");
            } else {
                await followArtist(userId, profileId);
                setIsFollowing(true);
                setMessage("User followed!");
            }
        } catch (error) {
            setMessage("Unable to update follow status.");
        }
    }

    return ( 
        <div>
            <form onSubmit={handleFollow}>
                <div className="submit-container">
                    <button type="submit" className="submit">
                        {isFollowing ? "Unfollow" : "Follow"} 
                    </button>
                </div>
            </form>
            {message && <p>{message}</p>}
        </div>
    )
};

export default Follow;