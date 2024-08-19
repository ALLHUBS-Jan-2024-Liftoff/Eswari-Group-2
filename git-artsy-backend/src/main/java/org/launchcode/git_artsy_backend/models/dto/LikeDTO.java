package org.launchcode.git_artsy_backend.models.dto;

import jakarta.validation.constraints.NotNull;

public class LikeDTO {
    @NotNull
    private Integer likeId;

    @NotNull
    private Integer artworkId;

    public LikeDTO(Integer likeId, Integer artworkId) {
        this.likeId = likeId;
        this.artworkId = artworkId;
    }

    public LikeDTO() {

    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(Integer artworkId) {
        this.artworkId = artworkId;
    }
}
