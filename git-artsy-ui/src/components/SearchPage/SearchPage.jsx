import React, { useState } from 'react';
import Banner from "../Banner";
import ArtworksSearch from "./ArtworksSearch";
import ArtistSearch from "./ArtistSearch";

const SearchPage = () => {
    const [searchType, setSearchType] = useState('artworks');

    // handles radio button change
    const handleSearchTypeChange = (event) => {
        setSearchType(event.target.value);
    };

    return (
        <>
            <div className="app-container">
                <Banner />
            </div>
            <div>
                <h1>Search</h1>
            </div>
            <div class="search-box">
                {/* Radio buttons to select search type */}
                <label>
                    <input
                        type="radio"
                        value="artworks"
                        checked={searchType === 'artworks'}
                        onChange={handleSearchTypeChange}
                    />
                    Artworks
                </label>
                <label>
                    <input
                        type="radio"
                        value="artists"
                        checked={searchType === 'artists'}
                        onChange={handleSearchTypeChange}
                    />
                    Artists
                </label>

                {searchType === 'artworks' && <ArtworksSearch />}
                {searchType === 'artists' && <ArtistSearch />}
            </div>
        </>
    );
};

export default SearchPage;