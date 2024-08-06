
import React, { useState } from 'react';
//import './cssforpages/Login.css'
import { userLogin } from "../../services/userService";
import { Link } from "react-router-dom";


//Login box
export const Login = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await userLogin(email, password);
            // redirect after login completion
            window.location.href = "/gallery";
        } catch (error) {
            setError("Login failed")
        }
    }
    return ( 
        <div className="container">
            <div className="header">
                <div className="text">Login</div>
                <div className="underline"></div>
            </div>
            <form onSubmit={handleSubmit}>
                <div className="inputs">
                    <div className="input">
                        <input type="email" name="email" value={email} placeholder="Email Address" onChange={(e) => setEmail(e.target.value)} required />
                    </div>   
                    <br></br> 
                    <div className="input">
                        <input type="password" name="password" value={password} placeholder="Password" onChange={(e) => setPassword(e.target.value)} required />
                    </div>
                </div>
                <div className="submit-container">
                    <button type="submit" className="submit">Login</button>
                </div>
            </form>
            <div className="footer">
                Not a member? <Link to='/signup'>Sign up here!</Link>
            </div>
        </div>
    )
};

export default Login;