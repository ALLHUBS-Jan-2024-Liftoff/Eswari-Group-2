import { useEffect, useState } from "react";
import Banner from "../Banner";
import '../NotificationPage/NotificationPage.css';
import { Link } from "react-router-dom";

const NotificationPage = () => {
  const [user, setUser] = useState(null); // State for storing user data
  const [artworks, setArtworks] = useState([]); // State for storing artworks data
  const [commissions, setCommissions] = useState([]); // State for storing commissions data
  const [followedArtists, setFollowedArtists] = useState([]); // State for storing followed artists data
  const [loading, setLoading] = useState(true); // State for managing loading state
  const [error, setError] = useState(null); // State for managing error messages

  // useEffect hook to run code on component mount
  useEffect(() => {
    // Retrieve user data from local storage
    const userData = JSON.parse(localStorage.getItem("user"));
    if (userData) {
      setUser(userData);
      if (userData.userRole === 'PATRON') { 
        fetchArtworks(); // Fetch artworks if the user is a patron
        fetchFollowedArtists(userData.userid); // Fetch followed artists if the user is a patron
      } else if (userData.userRole === 'ARTIST') {
        fetchCommissions(userData.userid); // Fetch commissions if the user is an artist
      }
    } else {
      console.log("No user data found in local storage.");
    }
  }, []); // Empty dependency array ensures this effect runs only once on mount

  // Function to fetch artworks data
  const fetchArtworks = async () => {
    setLoading(true); // Set loading state to true while fetching
    setError(null); // Clear any previous error messages
    try {
      const response = await fetch("http://localhost:8082/gitartsy/api/artworks/recent");
      if (response.ok) {
        const data = await response.json();
        console.log("Fetched artworks:", data); 
        setArtworks(data);
      } else {
        setError("Failed to fetch artworks.");
      }
    } catch (error) {
      setError("Error fetching artworks: " + error.message);
    } finally {
      setLoading(false);
    }
  };

  // Function to fetch followed artists data
  const fetchFollowedArtists = async (userId) => {
    setLoading(true); // Set loading state to true while fetching
    setError(null); // Clear any previous error messages
    try {
      const response = await fetch(`http://localhost:8082/api/follow/following?userId=${userId}`);
      if (response.ok) {
        const data = await response.json();
        console.log("Fetched followed artists:", data);
        setFollowedArtists(data);
      } else {
        setError("Failed to fetch followed artists.");
      }
    } catch (error) {
      setError("Error fetching followed artists: " + error.message);
    } finally {
      setLoading(false);
    }
  };

  // Function to fetch commissions data
  const fetchCommissions = async (artistId) => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch(`http://localhost:8082/api/commissions/artist/${artistId}`);
      if (response.ok) {
        const data = await response.json();
        console.log("Fetched commissions:", data); 
        setCommissions(data);
      } else {
        setError("Failed to fetch commissions.");
      }
    } catch (error) {
      setError("Error fetching commissions: " + error.message);
    } finally {
      setLoading(false);
    }
  };

  // Render the component
  return (
    <div className="app-container"> 
      <Banner /> {/* Render the Banner component */}
      <div className="notification-container">
        {loading && <p>Loading...</p>} {/* Show loading message while data is being fetched */}
        {error && <p className="error">{error}</p>} {/* Show error message if there is an error */}
        
        {user && user.userRole === 'PATRON' && ( /* Render content if the user is a patron */
          <>
            <h2>Recent Artworks</h2> {/* Heading for recent artworks */}
            {artworks.length > 0 ? ( /* Check if there are artworks to display */
              <div className="artworks-grid"> 
                {artworks.map((artwork) => ( /* Map through artworks */
                  <div key={artwork.id} className="artwork-card">
                    <img
                      src={artwork.fileDownloadUri} 
                      alt={artwork.title} 
                      className="artwork-image"
                    />
                    <div className="artwork-details"> 
                      <h3>{artwork.title}</h3> 
                      <p>{artwork.description}</p> 
                      <p><strong>Price:</strong> ${artwork.price.toFixed(2)}</p> 
                    </div>
                  </div>
                ))}
              </div>
            ) : (
              <p>No recent artworks available.</p> /* Message when no artworks are available */
            )}

            <h2>Followed Artists</h2> {/* Heading for followed artists */}
            {followedArtists.length > 0 ? (
              <ul className="followed-artists-list"> 
                {followedArtists.map((artist, index) => (
                  // <li key={index}>{artist}</li> 
                  <li key={index}>
                    <Link to={`/profile/${artist.profileId}`}>{artist.name}</Link>
                  </li>
                ))}
              </ul>
            ) : (
              <p>No followed artists available.</p> /* Message when no artists are followed */
            )}
          </>
        )}

        {user && user.userRole === 'ARTIST' && ( /* Render content if the user is an artist */
          <>
            <h2>Your Commissions</h2> {/* Heading for artist's commissions */}
            {commissions.length > 0 ? ( /* Check if there are commissions to display */
              <div className="commissions-grid">
                {commissions.map((commission, index) => ( /* Map through commissions and render each */
                  // <div key={commission.id} className="commission-card"> {/* Uncomment if using commission ID */}
                  <div key={index} className="commission-card">
                    <div className="commission-details">
                      <h3>Subject : {commission.subject}</h3> 
                      <p>Details : {commission.details}</p> 
                      <p>Description : {commission.description}</p> 
                      
                    </div>
                  </div>
                ))}
              </div>
            ) : (
              <p>No commissions available.</p> 
            )}
          </>
        )}
      </div>
    </div>
  );
};

export default NotificationPage; 
