import Banner from "../Banner";
import ArtworksSearch from "./ImageSearch"

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