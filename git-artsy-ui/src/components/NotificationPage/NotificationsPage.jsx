import { useEffect, useState } from "react";
import Banner from "../Banner";
import '../NotificationPage/NotificationPage.css';

const NotificationPage = () => {
  const [user, setUser] = useState(null);
  const [artworks, setArtworks] = useState([]);
  const [loading, setLoading] = useState(true);  // Initialize loading state
  const [error, setError] = useState(null);  // Initialize error state

  useEffect(() => {
    const userData = JSON.parse(localStorage.getItem("user"));
    if (userData) {
      setUser(userData);
      if (userData.userRole === 'PATRON') {
        fetchArtworks();
      } 
    } else {
      console.log("No user data found in local storage.");
    }
  }, []);


  const fetchArtworks = async () => {
    setLoading(true);  // Set loading to true when starting the fetch
    setError(null);    // Clear any previous errors
    try {
      const response = await fetch("http://localhost:8082/gitartsy/api/artworks/recent");
      if (response.ok) {
        const data = await response.json();
        setArtworks(data);
      } else {
        setError("Failed to fetch artworks.");
      }
    } catch (error) {
      setError("Error fetching artworks: " + error.message);
    } finally {
      setLoading(false);  // Set loading to false after the fetch is complete
    }
  };

  return (
    <div className="app-container">
      <Banner />
      <div className="notification-container">
        <h2>Recent Artworks</h2>
        {loading && <p>Loading artworks...</p>}
        {error && <p className="error">{error}</p>}
        {artworks.length > 0 ? (
          <div className="artworks-grid">
            {artworks.map((artwork) => (
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
                  {/* <button className="read-button">Read</button> */}
                </div>
              </div>
            ))}
          </div>
        ) : (
          <p>No recent artworks available.</p>
        )}
      </div>
    </div>
  );
};

export default NotificationPage;