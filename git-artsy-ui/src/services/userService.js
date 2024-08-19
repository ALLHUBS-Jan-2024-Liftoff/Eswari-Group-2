import axios from "axios";

const BASEAPIURL = "http://localhost:8082";

// User signup call to backend
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

//  user login call to backend
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

  // user logout call to back end
  export const userLogout = async () => {
    try {
      const response = await axios.get(`${BASEAPIURL}/api/user/logout`,
        { withCredentials: true });
    } catch (error) {
      console.error("Log out failed.", error);
      throw error;
    } 
  };

// search call to the backend
export const searchUsers = async (search) => {
  try {
    const response = await axios.get(`${BASEAPIURL}/api/users/search`, {
      params: { searchQuery: search }
    });
    return response.data;
  } catch (error) {
    console.error("Search failed.", error);
    throw error;
  }
};