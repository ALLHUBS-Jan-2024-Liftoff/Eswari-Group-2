import axios from "axios";

const BASEAPIURL = "http://localhost:8082";

export const registerUser = async (
    username,
    email,
    password,
    verifyPassword,
    role
    
) => {
    try {
        const response = await axios.post(`${BASEAPIURL}/api/user/newUser`,
            { username, email, password, verifyPassword, role },
            { withCredentials: true }
        );
        return response.data;
    } catch (error) {
        console.error("There was an error creating this user", error);
        throw error;
    }
 };

 export const userLogin = async (
    email,
    password
 ) => {
    try {
        const response = await axios.post(`${BASEAPIURL}/api/user/login`,
          { email, password },
          { withCredentials: true }
        );
        return response.data;
      } catch (error) {
        console.error("Incorrect email or password.", error);
        throw error;
      }
 };

 export const isUserFollowed = async (userId, followedUserId) => {
  try {
    const response = await axios.get(`${BASEAPIURL}/api/follow/status`, {
      params: { userId, followedUserId },
    });
    return response.data.isFollowing;
  } catch (error) {
    console.error("Error checking follow status", error);
    throw error;
  }
};

 export const followArtist = async (
    userId,
    followedUserId
  ) => {
    try {
        const response = await axios.post(`${BASEAPIURL}/api/follow/follow`,
          { userId, followedUserId },
        );
        return response.data;
      } catch (error) {
        console.error("Follow failed", error);
        throw error;
      }
  };

  export const unfollowArtist = async (userId, followedUserId) => {
    try {
      const response = await axios.post(`${BASEAPIURL}/api/follow/unfollow`, {
        userId,
        followedUserId,
      });
      return response.data;
    } catch (error) {
      console.error("Unfollow failed", error);
      throw error;
    }
  };

  export const userLogout = async () => {
    try {
      const response = await axios.get(`${BASEAPIURL}/api/user/logout`,
        { withCredentials: true });
    } catch (error) {
      console.error("Log out failed.", error);
      throw error;
    } 
};