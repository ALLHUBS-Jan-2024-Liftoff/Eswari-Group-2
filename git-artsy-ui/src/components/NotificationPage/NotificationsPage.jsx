import { useEffect, useState } from "react";
import Banner from "../Banner";
import '../NotificationPage/NotificationPage.css';

const NotificationPage = () => {
  const [user, setUser] = useState(null);
  const [artworks, setArtworks] = useState([]);

  useEffect(() => {
    const userData = JSON.parse(localStorage.getItem("user"));
    if (userData) {
      setUser(userData);
      fetchArtworks();
    } else {
      console.log("No user data found in local storage.");
    }
  }, []);

  const fetchArtworks = async () => {
    try {
      const response = await fetch("http://localhost:8082/gitartsy/api/artworks/all");
      if (response.ok) {
        const data = await response.json();
        setArtworks(data);
      } else {
        console.log("Failed to fetch artworks.");
      }
    } catch (error) {
      console.error("Error fetching artworks:", error);
    }
  };

  return (
    <div className="app-container">
      <Banner />
      <div className="notification-container">
        <table className="artworks-table">
          <thead>
            <tr>
              <th>Image</th>
              <th>Title</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {artworks.map((artwork) => (
              <tr key={artwork.id}>
                <td>
                  <img src={artwork.fileDownloadUri} alt={artwork.title} className="artwork-image" />
                </td>
                <td>{artwork.title}</td>
                <td>
                  <button className="read-button">Read</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default NotificationPage;
