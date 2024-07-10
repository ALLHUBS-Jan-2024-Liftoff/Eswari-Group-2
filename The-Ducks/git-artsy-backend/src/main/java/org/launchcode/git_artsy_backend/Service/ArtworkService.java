package org.launchcode.git_artsy_backend.Service;

import org.launchcode.git_artsy_backend.Entity.Artworks;
import org.launchcode.git_artsy_backend.Response.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface ArtworkService {

    ApiResponse<Artworks> saveArtwork(Artworks artwork);
    ApiResponse<List<Artworks>> getAllArtworks();
    ApiResponse<Optional<Artworks>> getArtworkById(Integer id);
    ApiResponse<Artworks> updateArtwork(Integer id, Artworks updatedArtwork);
    ApiResponse<Void> deleteArtwork(Integer id);

}
