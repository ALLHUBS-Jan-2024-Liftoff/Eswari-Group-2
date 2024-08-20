package org.launchcode.git_artsy_backend.models.dto;

import jakarta.validation.constraints.NotNull;

public class LikeDTO {
    private Long likeId;

    private Integer count;

    public LikeDTO() {

    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
