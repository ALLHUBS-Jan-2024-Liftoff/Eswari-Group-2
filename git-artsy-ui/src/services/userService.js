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
        const response = await axios.post(`${BASEAPIURL}/api/user/login`, null, {
          params: {email, password},
        });
        return response.data;
      } catch (error) {
        console.error("Incorrect email or password.", error);
        throw error;
      }
 }