package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.*;
import org.launchcode.git_artsy_backend.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Entity

public class LikeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    private Integer count;


    public LikeModel(Long likeId, Integer count) {
        this.likeId = likeId;
        this.count = 0;
    }

    public LikeModel() {}

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }
}
