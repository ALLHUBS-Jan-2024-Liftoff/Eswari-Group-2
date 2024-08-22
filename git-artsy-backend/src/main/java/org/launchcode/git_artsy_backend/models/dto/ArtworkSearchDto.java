package org.launchcode.git_artsy_backend.models.dto;

import java.util.List;

public class ArtworkSearchDto {

    private String fileDownloadUri;
    private String title;
    private String description;
    private List<String> tags;

    // Constructors
    public ArtworkSearchDto() {}

    public ArtworkSearchDto(String fileDownloadUri, String title, String description, List<String> tags) {
        this.fileDownloadUri = fileDownloadUri;
        this.title = title;
        this.description = description;
        this.tags = tags;
    }

    // Getters and Setters
    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
