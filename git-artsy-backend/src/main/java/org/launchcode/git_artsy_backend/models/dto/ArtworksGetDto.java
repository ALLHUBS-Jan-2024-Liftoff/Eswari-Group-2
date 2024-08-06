package org.launchcode.git_artsy_backend.models.dto;

public class ArtworksGetDto {
    private String title;
    private String fileDownloadUri;
    private String fileType;
    private Long size;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getFileDownloadUri() { return fileDownloadUri; }
    public void setFileDownloadUri(String fileDownloadUri) { this.fileDownloadUri = fileDownloadUri; }
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }
}
