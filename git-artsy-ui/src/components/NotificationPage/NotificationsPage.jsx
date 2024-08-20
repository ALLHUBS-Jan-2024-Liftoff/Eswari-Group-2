import { useEffect, useState } from "react";
import Banner from "../Banner";
import '../NotificationPage/NotificationPage.css';

const NotificationPage = () => {
  const [user, setUser] = useState(null); // State for storing user data
  const [artworks, setArtworks] = useState([]); // State for storing artworks data
  const [commissions, setCommissions] = useState([]); // State for storing commissions data
  const [loading, setLoading] = useState(true); // State for managing loading state
  const [error, setError] = useState(null); // State for managing error messages

  // useEffect hook to run code on component mount
  useEffect(() => {
    // Retrieve user data from local storage
    const userData = JSON.parse(localStorage.getItem("user"));
    if (userData) {
      setUser(userData);
      if (userData.userRole === 'PATRON') { 
        fetchArtworks();// Fetch artworks if the user is a patron
      } else if (userData.userRole === 'ARTIST') {
        fetchCommissions(userData.userid);// Fetch commissions if the user is an artist
      }
    } else {
      console.log("No user data found in local storage.");
    }
  }, []);// Empty dependency array ensures this effect runs only once on mount

    // Function to fetch artworks data
  const fetchArtworks = async () => {
    setLoading(true);// Set loading state to true while fetching
    setError(null);// Clear any previous error messages
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
    <div className="app-container"> {/* Container for the entire page */}
      <Banner /> {/* Render the Banner component */}
      <div className="notification-container"> 
        {loading && <p>Loading...</p>} {/* Show loading message while data is being fetched */}
        {error && <p className="error">{error}</p>} {/* Show error message if there is an error */}
        
        {user && user.userRole === 'PATRON' && ( /* Render content if the user is a patron */
          <>
            <h2>Recent Artworks</h2> {/* Heading for recent artworks */}
            {artworks.length > 0 ? ( /* Check if there are artworks to display */
              <div className="artworks-grid"> {/* Grid container for artworks */}
                {artworks.map((artwork) => ( /* Map through artworks */
                  <div key={artwork.id} className="artwork-card"> 
                    <img
                      src={artwork.fileDownloadUri} 
                      alt={artwork.title} 
                      className="artwork-image"
                    />
                    <div className="artwork-details"> {/* Container for artwork details */}
                      <h3>{artwork.title}</h3> {/* Artwork title */}
                      <p>{artwork.description}</p> {/* Artwork description */}
                      <p><strong>Price:</strong> ${artwork.price.toFixed(2)}</p> {/* Artwork price */}
                    </div>
                  </div>
                ))}
              </div>
            ) : (
              <p>No recent artworks available.</p> /* Message when no artworks are available */
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
                      <h3>Subject : {commission.subject}</h3> {/* Commission subject */}
                      <p>Details : {commission.details}</p> {/* Commission details */}
                      <p>Description : {commission.description}</p> {/* Commission description */}
                      {/* Add any other relevant details here */}
                    </div>
                  </div>
                ))}
              </div>
            ) : (
              <p>No commissions available.</p> /* Message when no commissions are available */
            )}
          </>
        )}
      </div>
    </div>
  );
};

export default NotificationPage; /* Export the component for use in other parts of the application */
