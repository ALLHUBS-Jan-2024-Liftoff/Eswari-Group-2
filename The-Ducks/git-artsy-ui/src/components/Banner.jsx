import './styling.css';
import { Link } from "react-router-dom";
//Header & Navigation

//TODO Include logic for if user is logged in or not
const Banner = () => {
    return (
        <div class='header'>
            <h1>gitArtsy</h1>
            <nav>
                <a><Link to='/gallery'>git_Inspired</Link></a>
                <a>Find Artists</a>
                <a><Link to='/'>Login</Link></a>
            </nav>
        </div>
    );
};




export default Banner