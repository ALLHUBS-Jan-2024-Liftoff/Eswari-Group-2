package org.launchcode.git_artsy_backend.models.dto;

import java.util.Collection;
import java.util.Set;

public class ArtworksGetDto {
    private String title;
    private String fileDownloadUri;
    private String fileType;
    private Long size;

    public static void setDescription(Object description) {
    }

    public static void setPrice(Object price) {

    }

    public static <R> void setTags(Set<R> collect) {
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public static void setTitle(String title) { this.title = title; }
    public String getFileDownloadUri() { return fileDownloadUri; }
    public void setFileDownloadUri(String fileDownloadUri) { this.fileDownloadUri = fileDownloadUri; }
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }

    public Object getDescription() {
        return null;
    }

    public Object getPrice() {
        return null;
    }

    public Collection<Object> getTags() {
        return null;
    }
}
