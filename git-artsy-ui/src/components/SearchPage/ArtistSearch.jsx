import React, { useState } from 'react';
import { searchUsers } from '../../services/userService';

const ArtistSearch = () => {
    const [search, setSearch] = useState('');
    const [results, setResults] = useState([]);
    const [hasSearched, setHasSearched] = useState(false); //checks if user has searched

    // handles search input change
    const handleInputChange = (event) => {
        setSearch(event.target.value);
    };

    // handles search submit change
    const handleSubmit = async (event) => {
        event.preventDefault();
        setHasSearched(true);
        try {
            const searchResults = await searchUsers(search);
            console.log('Search Results:', searchResults); // Check the results
            setResults(searchResults);
        } catch (error) {
            console.error('Search failed:', error);
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>
                    Enter Artist:
                    <input type="text" value={search} onChange={handleInputChange} placeholder="search"/>
                </label>
                <button type="submit">Search</button>
            </form>

            <div>
                <h3>Search Results:</h3>
                {hasSearched ? (
                results.length > 0 ? (
                    <ul>
                        {results.map((user, index) => (
                            <li key={index}>
                                <a href={`/profile/${profileId}`}>
                                    {user.username}
                                </a>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>No results found.</p>
                )
            ) : null
            }
            </div>
        </div>
    );
};

export default ArtistSearch;
