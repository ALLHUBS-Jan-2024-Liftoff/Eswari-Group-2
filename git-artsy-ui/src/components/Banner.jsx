import './styling.css';
import { Link } from "react-router-dom";
//Header & Navigation

//TODO Include logic for if user is logged in or not

const Banner = ( user, {handleLogout}) => {
    return (
    <div className='header'>
        <h1>gitArtsy</h1>
            <nav>
                <Link to='/gallery'>git_Inspired</Link>
                <Link to ='/search'>Search</Link>
                
                {! user ? (
                <>
                <Link to='/'>Login</Link>
                </>
                ) : (
                <>
                <div className="dropdown">
                    <button id="dropdown-nav">My Account</button>
                </div>
                <div className="dropdown-content">
                    <Link to="/notifications" rel="noopener">Notifications</Link>
                    <Link to="/profile" rel="noopener">My Profile</Link>
                    <Link to="/" rel="noopener" onClick={handleLogout}>Logout</Link>
                </div>
                </>   
                )} 
            </nav>
        </div>
    );
};




export default Banner