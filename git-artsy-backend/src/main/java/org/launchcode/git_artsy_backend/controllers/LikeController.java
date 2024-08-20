package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.LikeModel;

import org.launchcode.git_artsy_backend.repositories.ArtworksRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.launchcode.git_artsy_backend.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("gitartsy/api/likes")
@CrossOrigin(origins = "http://localhost:5173")
public class LikeController {

  @Autowired
  private LikeRepository likeRepository;

  @Autowired
  private ArtworksRepo artworksRepo;

  //creates the like button for the artwork
  @PostMapping("/new")
  public ResponseEntity<String> createLike(@RequestParam Long artworkId, @RequestParam Integer count) {
    LikeModel like = new LikeModel(artworkId, count);
    likeRepository.save(like);
    return ResponseEntity.ok("Artwork Liked");
  }

  @PostMapping("/liked")
  public ResponseEntity<String> liked(@RequestParam Long artworkId, @RequestParam Integer count) {
    LikeModel like = likeRepository.getReferenceById(artworkId);
    like.setCount(like.getCount() + 1);
    return ResponseEntity.ok("Artwork liked");
  }

  @GetMapping("likes/{id}")
  public ResponseEntity<String> findByArtworkId(@PathVariable Long artworkId) {
    LikeModel like = likeRepository.findById(artworkId).orElse(null);
      return ResponseEntity.ok("Artwork found");
  }

  @GetMapping
  public ResponseEntity<String> getAllLikes() {
    List<LikeModel> likes = (List<LikeModel>) likeRepository.findAll();
    return ResponseEntity.ok("Likes found");
  }
}













