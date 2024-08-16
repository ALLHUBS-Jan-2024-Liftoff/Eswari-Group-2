import Banner from "../Banner";
import ArtworksSearch from "./ArtworksSearch"

const SearchPage = () => {
    return (
      <>
      <div className="app-container">
          <Banner />
      </div>   
      <div>
        <ArtworksSearch />
      </div> 
      </>
    );
};

export default SearchPage