package org.launchcode.git_artsy_backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Artworks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artworkId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    private String filename;
    private String fileDownloadUri;
    private String fileType;
    private long size;



    public Artworks() {}

    public Artworks(Profile profile, String filename, String fileDownloadUri, String fileType, long size) {
        this.profile = profile;
        this.filename = filename;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public Integer getProductId() {
        return artworkId;
    }

    public void setProductId(Integer artworkId) {
        this.artworkId = artworkId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
