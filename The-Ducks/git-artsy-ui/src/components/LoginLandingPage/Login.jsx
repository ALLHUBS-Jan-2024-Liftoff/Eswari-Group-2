
import React, { useState } from 'react';
//import './cssforpages/Login.css'


//TODO include page link on line 28
//Login box
const Login = () => {
    return ( 
        <div className="container">
            <div className="header">
                <div className="text">Login</div>
                <div className="underline"></div>
            </div>
            <form>
                <div className="inputs">
                    <div className="input">
                        <input type="email" name="email" placeholder="Email Address" required />
                    </div>   
                    <br></br> 
                    <div className="input">
                        <input type="password" name="password" placeholder="Password" required />
                    </div>
                </div>
                <div className="submit-container">
                    <button type="submit" className="submit">Login</button>
                </div>
            </form>
            <div className="footer">
                Not a member? <a>Sign up here!</a>
            </div>
        </div>
    )
};

export default Login;