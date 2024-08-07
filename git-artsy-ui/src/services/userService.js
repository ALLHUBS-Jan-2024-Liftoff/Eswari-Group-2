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
        const response = await axios.post(`${BASEAPIURL}/api/user/newUser`, null, {
            params: {username, email, password, verifyPassword, role},
        });
        return response.data;
    } catch (error) {
        console.error("There was an error creating this user", error);
        throw error;
    }
 };