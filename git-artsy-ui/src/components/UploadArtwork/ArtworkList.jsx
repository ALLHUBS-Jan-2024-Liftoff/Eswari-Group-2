import Banner from "../Banner";

const ArtworkList = () => {

    const [artworks, setArtworks] = useState([]);
    const [error, setError] = useState("");
    const [user, setUser] = useState(null);
    

    useEffect(() => {
        const fetchUserData = () => {
            const userData = JSON.parse(localStorage.getItem('user'));
            if (userData) {
                setUser(userData);
            } else {
                setError("No user data found");
            }
        };

        fetchUserData();
    }, []);

    useEffect(() => {
        const getAllArts = async () => {
            if (user) {
                try {
                    const response = await api.fetchArtworksByProfile(user.userid);
                    console.log("Response from API:", response);
                    setArtworks(response.data);
                    if (response && response.data) {
                        setArtworks(response.data);
                    } else if (response) {
                        setArtworks(response);
                    } else {
                        throw new Error("Invalid response structure");
                    }
                } catch (error) {
                    setError("Failed to fetch artworks");
                    console.error("Error fetching artworks:", error);
                }
            }
        };

        getAllArts();
    }, [user]);

    return (
      <div className="app-container">
          <Banner />

          <div>
            <h1>Artwork List</h1>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <ul>
                {artworks.length > 0 ? (
                    artworks.map((artwork, index) => (
                        <li key={`${artwork.fileDownloadUri}-${index}`}>
                            <h2>{artwork.title}</h2>
                            {artwork.fileDownloadUri && (
                                <img src={artwork.fileDownloadUri} alt={artwork.title} style={{ width: '200px', height: 'auto' }} />
                            )}
                           
                        </li>
                    ))
                ) : (
                    <p>No artworks available</p>
                )}
            </ul>
        </div>

      </div> 
      
      
    );
};

export default ArtworkList