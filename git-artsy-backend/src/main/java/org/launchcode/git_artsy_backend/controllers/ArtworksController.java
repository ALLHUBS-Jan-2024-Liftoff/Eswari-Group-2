
package org.launchcode.git_artsy_backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.launchcode.git_artsy_backend.models.Artworks;
import org.launchcode.git_artsy_backend.models.Profile;
import org.launchcode.git_artsy_backend.models.Tag;
import org.launchcode.git_artsy_backend.models.dto.ArtworksDto;
import org.launchcode.git_artsy_backend.models.dto.ArtworksGetDto;
import org.launchcode.git_artsy_backend.repositories.ArtworksRepo;
import org.launchcode.git_artsy_backend.repositories.ProfileRepo;
import org.launchcode.git_artsy_backend.repositories.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController// Indicates that this class handles RESTful requests
@RequestMapping("gitartsy/api/artworks")// Base URL for all endpoints in this controller
@CrossOrigin(origins = "http://localhost:5173")// Allows CORS requests from the specified origin
public class ArtworksController {

    @Autowired
    private ArtworksRepo artworkRepo;// Repository for managing Artworks entities

    @Autowired
    private ProfileRepo profileRepo;// Repository for managing Profile entities

    @Autowired
    private TagRepository tagRepo;// Directory path for storing uploaded files


    private final Path fileStorageLocation;

    private static final Logger logger = LoggerFactory.getLogger(ArtworksController.class);

    // Constructor to initialize file storage location
    public ArtworksController() {
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create upload directory", ex);
        }
    }

    // Endpoint for creating new artwork with multipart form data
    @PostMapping(value = "/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ArtworksDto createArtwork(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") Float price,
            @RequestParam("tagIds") String  tagIdsJson,
            @RequestParam("image") MultipartFile file,
            @RequestParam("profileId") Integer profileId ) {



        // Log request parameters for debugging
        logger.debug("Profile ID: {}", profileId);
        logger.debug("Title: {}", title);
        logger.debug("Description: {}", description);
        logger.debug("Price: {}", price);
        logger.debug("File: {}", (file != null ? file.getOriginalFilename() : "No file"));
        logger.debug("Tag IDs: {}", tagIdsJson);




        ArtworksDto artworksDto = new ArtworksDto();

        Optional<Profile> profileOptional = profileRepo.findById(profileId);

        if (profileOptional.isPresent()) {
            logger.error("Profile not found with ID: {}", profileId);
            Artworks artwork = new Artworks();
            artwork.setProfile(profileOptional.get());
            artwork.setTitle(title);
            artwork.setDescription(description);
            artwork.setPrice(price);
            artwork.setCreatedAt(LocalDateTime.now());
            artwork.setUpdatedAt(LocalDateTime.now());

            // Handle file upload
            if (file != null && !file.isEmpty()) {
                String fileName;
                String fileDownloadUri;
                fileName = Paths.get(file.getOriginalFilename()).getFileName().toString();
                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                try {
                    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
                }

                fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/uploads/")
                        .path(fileName)
                        .toUriString();

                artwork.setFilename(fileName);
                artwork.setFileDownloadUri(fileDownloadUri);
                artwork.setFileType(file.getContentType());
                artwork.setSize(file.getSize());
            }

            // Convert tagIdsJson to List<Long>
            List<Long> tagIds = new ArrayList<>();
            try {
                String[] tagIdsArray = tagIdsJson.split(",");
                for (String tagIdStr : tagIdsArray) {
                    tagIds.add(Long.parseLong(tagIdStr.trim()));
                }
            } catch (NumberFormatException e) {
                logger.error("Failed to parse tagIds", e);
                // Handle error accordingly
            }

            // Add tags to the artwork
            for (Long tagId : tagIds) {
                Optional<Tag> tagOptional = tagRepo.findById(tagId);
                if (tagOptional.isPresent()) {
                    artwork.getTags().add(tagOptional.get());
                } else {
                    logger.warn("Tag ID {} not found", tagId);
                }
            }



            try {
                Artworks savedArtwork = artworkRepo.save(artwork);

                artworksDto.setProfileId(profileId);
                artworksDto.setDescription(description);
                artworksDto.setImage(file);
                artworksDto.setPrice(price);
                artworksDto.setTitle(title);
                artworksDto.setTagIds(tagIds);

                //return response.toString();
                return artworksDto;
            } catch (Exception e) {
                e.printStackTrace();

                return artworksDto;
            }
        } else {

            return artworksDto;
        }
    }


    // Endpoint to get artworks by profile ID
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<ArtworksGetDto>> getArtworksByProfile(@PathVariable Integer profileId) {
        List<Artworks> artworks = artworkRepo.findByProfileId(profileId);
        //List<Artworks> files = artworkRepo.findAll();

        List<ArtworksGetDto> allArtworks = new ArrayList<>();

        for (Artworks oneartwork : artworks)
        {
            ArtworksGetDto artworksGetDtoDto = new ArtworksGetDto();
            artworksGetDtoDto.setTitle(oneartwork.getTitle());
            artworksGetDtoDto.setFileDownloadUri(oneartwork.getFileDownloadUri());
            artworksGetDtoDto.setFileType(oneartwork.getFileType());
            artworksGetDtoDto.setSize(oneartwork.getSize());
            allArtworks.add(artworksGetDtoDto);

        }

        return ResponseEntity.ok(allArtworks);

    }

    // Endpoint to download a file by file name
    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        // Resolve the file path to the requested file name
        Path filePath = fileStorageLocation.resolve(fileName).normalize();

        Resource resource;
        try {
            // Create a resource from the file path
            resource = new UrlResource(filePath.toUri());
            // Check if the resource exists and is readable
            if (!resource.exists()) {
                // If the file does not exist, throw a 404 Not Found exception
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
            }
        } catch (Exception e) {
            // If there's an error while trying to access the file, throw a 404 Not Found exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found", e);
        }

        String contentType = null;
        try {
            // Try to determine the content type of the file
            contentType = Files.probeContentType(filePath);
        } catch (IOException e) {
            // If there's an error determining the content type, default to "application/octet-stream"

            //The contentType = "application/octet-stream";
            // which means the file is treated as a binary file that could be of any type.
            contentType = "application/octet-stream";
        }

        // If content type is still null, set it to "application/octet-stream"
        if (contentType == null) {
            //set type of a file to a default value
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        // Return the file as a ResponseEntity with the determined content type and headers
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @GetMapping("recent")
    public ResponseEntity<List<ArtworksGetDto>> getRecentArtworks() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Get the start of today
        LocalDateTime startOfToday = now.toLocalDate().atStartOfDay();

        // Get the start of yesterday
        LocalDateTime startOfYesterday = startOfToday.minusDays(1);

        // Fetch all artworks from the repository
        List<Artworks> artworks = artworkRepo.findAll();

        // Initialize the list to hold recent artworks
        List<ArtworksGetDto> recentArtworks = new ArrayList<>();

        // Iterate through artworks and filter out those not within the last two days
        for (Artworks artwork : artworks) {
            LocalDateTime createdAt = artwork.getCreatedAt();
            if (createdAt.isAfter(startOfYesterday) && createdAt.isBefore(now)) {
                ArtworksGetDto dto = new ArtworksGetDto();
                dto.setId(artwork.getProductId());
                dto.setTitle(artwork.getTitle());
                dto.setFileDownloadUri(artwork.getFileDownloadUri());
                dto.setFileType(artwork.getFileType());
                dto.setSize(artwork.getSize());
                dto.setDescription(artwork.getDescription());
                //dto.setTags(artwork.getTags());
                dto.setPrice(artwork.getPrice());
                recentArtworks.add(dto);
            }
        }
        // Return the filtered list
        return ResponseEntity.ok(recentArtworks); }

    @GetMapping
    public ResponseEntity<List<ArtworksGetDto>> getAllArtworks() {
        List<Artworks> artworks = artworkRepo.findAll();

        List<ArtworksGetDto> allArtworks = new ArrayList<>();

        for (Artworks index : artworks)
        {
            ArtworksGetDto artworksGetDtoDto = new ArtworksGetDto();
            artworksGetDtoDto.setTitle(index.getTitle());
            artworksGetDtoDto.setFileDownloadUri(index.getFileDownloadUri());
            artworksGetDtoDto.setFileType(index.getFileType());
            artworksGetDtoDto.setSize(index.getSize());
            allArtworks.add(artworksGetDtoDto);

        }

        return ResponseEntity.ok(allArtworks);
    }
}