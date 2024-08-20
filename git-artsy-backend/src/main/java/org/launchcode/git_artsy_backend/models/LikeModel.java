package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.*;
import org.launchcode.git_artsy_backend.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class LikeModel {
    @Id
    @Column(name = "artwork_id")
    private Long artworkId;

    private Integer count = 0;

    public LikeModel(Long artworkId, Integer count) {
        this.artworkId = artworkId;
        this.count = count;
    }

    public Long getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(Long artworkId) {
        this.artworkId = artworkId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
