package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.Profile;
import org.launchcode.git_artsy_backend.models.User;
import org.launchcode.git_artsy_backend.models.dto.ProfileDto;
import org.launchcode.git_artsy_backend.repositories.ProfileRepo;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("gitartsy/api/profiles")
@CrossOrigin(origins = "http://localhost:5173")// Allows CORS requests from the specified origin
public class ProfileController {

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private UserRepository userRepo;

    private final Path fileStorageLocation;

    private static final Logger logger = LoggerFactory.getLogger(ArtworksController.class);

    public ProfileController() {
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create upload directory", ex);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<ProfileDto> createArtistProfile(
            @RequestParam("userId") Long userId,
            @RequestParam("name") String name,
            @RequestParam("location") String location,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("profilePic") MultipartFile file,
            @RequestParam("bioDescription") String bioDescription) {

        // Check if the user exists
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Profile artistProfile = new Profile();

            artistProfile.setUser(user);
            artistProfile.setName(name);
            artistProfile.setLocation(location);
            artistProfile.setEmail(email);
            artistProfile.setPhone(phone);
            artistProfile.setBioDescription(bioDescription);
            artistProfile.setCreatedAt(LocalDateTime.now());
            artistProfile.setUpdatedAt(LocalDateTime.now());

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

                artistProfile.setFilename(fileName);
                artistProfile.setFileDownloadUri(fileDownloadUri);
                artistProfile.setFileType(file.getContentType());
                artistProfile.setSize(file.getSize());
            }

            try {
                Profile savedArtistProfile = profileRepo.save(artistProfile);
                ProfileDto profileDto = new ProfileDto();
                profileDto.setId(savedArtistProfile.getId());
                profileDto.setSize(savedArtistProfile.getSize());
                profileDto.setFilename(savedArtistProfile.getFilename());
                profileDto.setBioDescription(savedArtistProfile.getBioDescription());
                profileDto.setLocation(savedArtistProfile.getLocation());
                profileDto.setEmail(savedArtistProfile.getEmail());
                profileDto.setPhone(savedArtistProfile.getPhone());
                profileDto.setFileDownloadUri(savedArtistProfile.getFileDownloadUri());
                profileDto.setFileType(savedArtistProfile.getFileType());
                profileDto.setName(savedArtistProfile.getName());

                return ResponseEntity.status(HttpStatus.CREATED).body(profileDto);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


     //Handles HTTP GET requests to serve files stored on the server.
     // @param filename the name of the file to be served, passed in the URL path.
     //@return a ResponseEntity containing the file resource
     @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
         // Resolve the path of the requested file using the fileStorageLocation directory and the filename.
        Path file = fileStorageLocation.resolve(filename);
        Resource fileResource;

        try {
            // Create a Resource object for the file using its URI.
            fileResource = new UrlResource(file.toUri());
            // Check if the file exists and is readable.
            if (fileResource.exists() || fileResource.isReadable()) {
                // Return the file resource in the HTTP response
                // which prompts the user to download the file rather than displaying it in the browser.
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
                        .body(fileResource);
            } else {
                // If the file does not exist or is not readable, return a 404
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // getting the particular profile
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable("id") Integer id) {
        Optional<Profile> profileOptional = profileRepo.findById(id);

        ProfileDto profileDto = new ProfileDto();
        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();

            profileDto.setId(profile.getId());
            profileDto.setSize(profile.getSize());
            profileDto.setFilename(profile.getFilename());
            profileDto.setBioDescription(profile.getBioDescription());
            profileDto.setLocation(profile.getLocation());
            profileDto.setEmail(profile.getEmail());
            profileDto.setPhone(profile.getPhone());
            profileDto.setFileDownloadUri(profile.getFileDownloadUri());
            profileDto.setFileType(profile.getFileType());
            profileDto.setName(profile.getName());

            return ResponseEntity.ok(profileDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(profileDto);
        }
    }

    //checking wheather a user have profile
    @GetMapping("/exists/{userId}")
    public ResponseEntity<Boolean> doesProfileExist(@PathVariable("userId") Long userId) {
        // Check if the user exists
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            // Check if a profile exists for the user
            Optional<Profile> profileOptional = profileRepo.findByUser(userOptional.get());
            if (profileOptional.isPresent()) {
                // Profile exists, return true
                return ResponseEntity.ok(true);
            } else {
                // Profile does not exist, return false
                return ResponseEntity.ok(false);
            }
        } else {
            // User not found, return 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    //getting the profile id of particular user
    @GetMapping("/profileid/{userId}")
    public ResponseEntity<Integer> getProfileIdByUserId(@PathVariable("userId") Long userId) {
        // Check if the user exists
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            // Check if a profile exists for the user
            Optional<Profile> profileOptional = profileRepo.findByUser(userOptional.get());
            if (profileOptional.isPresent()) {
                // Profile exists, return the ProfileId
                return ResponseEntity.ok(profileOptional.get().getId());
            } else {
                // Profile does not exist, return 0

                return ResponseEntity.ok(0);
            }
        } else {
            // User not found, return 0
            return ResponseEntity.ok(0);
        }
    }

    //updating the profile
    @PutMapping("/update/{profileId}")
    public ResponseEntity<ProfileDto> updateProfile(
            @PathVariable Integer profileId,
            @RequestParam("name") String name,
            @RequestParam("location") String location,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("bioDescription") String bioDescription,
            @RequestParam(value = "profilePic", required = false) MultipartFile file) {

        Optional<Profile> profileOptional = profileRepo.findById(profileId);
        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            profile.setName(name);
            profile.setLocation(location);
            profile.setEmail(email);
            profile.setPhone(phone);
            profile.setBioDescription(bioDescription);
            profile.setUpdatedAt(LocalDateTime.now());

            if (file != null && !file.isEmpty()) {
                String fileName = Paths.get(file.getOriginalFilename()).getFileName().toString();
                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                try {
                    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
                }

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/uploads/")
                        .path(fileName)
                        .toUriString();

                profile.setFilename(fileName);
                profile.setFileDownloadUri(fileDownloadUri);
                profile.setFileType(file.getContentType());
                profile.setSize(file.getSize());
            }

            Profile updatedProfile = profileRepo.save(profile);
            ProfileDto profileDto = new ProfileDto();
            profileDto.setId(updatedProfile.getId());
            profileDto.setSize(updatedProfile.getSize());
            profileDto.setFilename(updatedProfile.getFilename());
            profileDto.setBioDescription(updatedProfile.getBioDescription());
            profileDto.setLocation(updatedProfile.getLocation());
            profileDto.setEmail(updatedProfile.getEmail());
            profileDto.setPhone(updatedProfile.getPhone());
            profileDto.setName(updatedProfile.getName());
            profileDto.setFileDownloadUri(updatedProfile.getFileDownloadUri());
            profileDto.setFileType(updatedProfile.getFileType());
            return ResponseEntity.ok(profileDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
