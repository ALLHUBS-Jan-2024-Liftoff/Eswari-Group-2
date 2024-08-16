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

 export const followArtist = async (
    user_id,
    followed_user_id
  ) => {
    try {
        const response = await axios.post(`${BASEAPIURL}/api/follow/follow`,
          { user_id, followed_user_id },
        );
        return response.data;
      } catch (error) {
        console.error("Follow failed", error);
        throw error;
      }
 }