import './styling.css';
import { Link } from "react-router-dom";
import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import { userLogout } from '../services/userService';
import React, { useState } from 'react';


//Header & Navigation

    

const Banner = ( user ) => {
    
    const [error, setError] = useState(null);

    const handleLogout = async (e) => {
        e.preventDefault();
        try {
            const response = await userLogout();
            // redirect to homepage login after logout
            window.location.href = "/";
        } catch (error) {
            setError("Logout failed")
        }
    };

    return (
    <div className='header'>
        <h1>gitArtsy</h1>
            <nav>
                <Link to='/gallery'>git_Inspired</Link>
                <Link to ='/search'>Search</Link>
                
                {!user ? (
                <>
                <Link to='/'>Login</Link>
                </>
                ) : (
                <>
                <Dropdown>
                    <Dropdown.Toggle variant="success" id="dropdown-basic">
                            My Account
                    </Dropdown.Toggle>

                    <Dropdown.Menu>
                        <Dropdown.Item href="/notifications">Notifications</Dropdown.Item>
                        <Dropdown.Item href="/profile">My Profile</Dropdown.Item>
                        <Dropdown.Item href="/commissions">Patron Commissions</Dropdown.Item>
                        <Dropdown.Item href="/uploadartwork">Upload Artworks</Dropdown.Item>
                        <Dropdown.Item href="/tag">Tags</Dropdown.Item>
                        <Dropdown.Item onClick={handleLogout} href='/'>Logout</Dropdown.Item>
                       
                    </Dropdown.Menu>
                </Dropdown>
                </>   
                )} 
            </nav>
        </div>
    );
};




export default Banner