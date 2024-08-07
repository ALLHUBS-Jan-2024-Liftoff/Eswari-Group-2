
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("gitartsy/api/artworks")
@CrossOrigin(origins = "http://localhost:5173")
public class ArtworksController {

    @Autowired
    private ArtworksRepo artworkRepo;

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private TagRepository tagRepo;


    private final Path fileStorageLocation;

    //private static final Logger logger = LoggerFactory.getLogger(ArtworksController.class);

    public ArtworksController() {
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create upload directory", ex);
        }
    }

    @PostMapping("/new")
    public ArtworksDto createArtwork(
            @RequestParam("image") MultipartFile file,
            @RequestParam("profileId") Integer profileId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("tags") List<String> tags
    ) {

        ArtworksDto artworksDto = new ArtworksDto();

        Optional<Profile> profileOptional = profileRepo.findById(profileId);
        if (profileOptional.isPresent()) {
            Artworks artwork = new Artworks();

            //set new fields
            artwork.setTitle(title);
            artwork.setDescription(description);
            artwork.setPrice(price);


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

            //Add tags to the artwork
            List<Tag> tagEntities = tags.stream()
                    .map(tagName -> tagRepo.findByName(tagName).orElseGet(() -> new Tag(tagName)))
                    .collect(Collectors.toList());
            artwork.setTags(tagEntities); //Do I need newHashSet? artwork.setTags(new HashSet<>(tagEntities));

            try {
                Artworks savedArtwork = artworkRepo.save(artwork);

                artworksDto.setImage(file);
                artwork.setDescription(description);
                artworksDto.setImage(file);
                artwork.setPrice(price);
                artwork.setTitle(title);
                //artworksDto.setTagIds(tagIds);


                return artworksDto;
            } catch (Exception e) {
                e.printStackTrace();

                return artworksDto;
            }
        } else {
            return artworksDto;
        }
    }


    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<ArtworksGetDto>> getArtworksByProfile(@PathVariable Integer profileId) {
        List<Artworks> artworks = artworkRepo.findByProfileId(profileId);
        //List<Artworks> files = artworkRepo.findAll();

        List<ArtworksGetDto> allArtworks = new ArrayList<>();

        for (Artworks oneartwork : artworks)
        {
            ArtworksGetDto artworksGetDto = new ArtworksGetDto();
            artworksGetDto.setFileDownloadUri(oneartwork.getFileDownloadUri());
            artworksGetDto.setFileType(oneartwork.getFileType());
            artworksGetDto.setSize(oneartwork.getSize());

            // Set new fields

            ArtworksGetDto.setTitle(oneartwork.getTitle());
            ArtworksGetDto.setDescription(oneartwork.getDescription());
            ArtworksGetDto.setPrice(oneartwork.getPrice());
            ArtworksGetDto.setTags(oneartwork.getTags());
            allArtworks.add(artworksGetDto);
        }

        return ResponseEntity.ok(allArtworks);

    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found", e);
        }

        String contentType = null;
        try {
            contentType = Files.probeContentType(filePath);
        } catch (IOException e) {
            contentType = "application/octet-stream";
        }

        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
