package org.launchcode.git_artsy_backend.Service.Impl;

import org.launchcode.git_artsy_backend.Entity.Artworks;
import org.launchcode.git_artsy_backend.Repo.ArtworksRepo;
import org.launchcode.git_artsy_backend.Response.ApiResponse;
import org.launchcode.git_artsy_backend.Service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArtworkIMPL implements ArtworkService {

    @Autowired
    private ArtworksRepo artworkRepo;

    @Override
    public ApiResponse<Artworks> saveArtwork(Artworks artwork) {
        Artworks savedArtwork = artworkRepo.save(artwork);
        return new ApiResponse<>(true, "Artwork created successfully", savedArtwork);
    }

    @Override
    public ApiResponse<List<Artworks>> getAllArtworks() {
        List<Artworks> artworksList = artworkRepo.findAll();
        return new ApiResponse<>(true, "Fetched all artworks", artworksList);
    }

    @Override
    public ApiResponse<Optional<Artworks>> getArtworkById(Integer id) {
        Optional<Artworks> artwork = artworkRepo.findById(id);
        if (artwork.isPresent()) {
            return new ApiResponse<>(true, "Fetched artwork", artwork);
        } else {
            return new ApiResponse<>(false, "Artwork not found", Optional.empty());
        }
    }

    @Override
    public ApiResponse<Artworks> updateArtwork(Integer id, Artworks updatedArtwork) {
        return artworkRepo.findById(id)
                .map(artwork -> {
                    artwork.setTitle(updatedArtwork.getTitle());
                    artwork.setDescription(updatedArtwork.getDescription());
                    artwork.setPrice(updatedArtwork.getPrice());
                    artwork.setImageUrl(updatedArtwork.getImageUrl());
                    artwork.setUpdatedAt(LocalDateTime.now());
                    Artworks savedArtwork = artworkRepo.save(artwork);
                    return new ApiResponse<>(true, "Artwork updated successfully", savedArtwork);
                })
                .orElseThrow(() -> new RuntimeException("Artwork not found"));
    }

    @Override
    public ApiResponse<Void> deleteArtwork(Integer id) {
        if (artworkRepo.existsById(id)) {
            artworkRepo.deleteById(id);
            return new ApiResponse<>(true, "Artwork deleted successfully", null);
        } else {
            return new ApiResponse<>(false, "Artwork not found", null);
        }
    }

}
