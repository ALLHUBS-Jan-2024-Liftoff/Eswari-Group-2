package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.models.LikeModel;

import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import org.launchcode.git_artsy_backend.models.dto.LikeDTO;
import org.launchcode.git_artsy_backend.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "http://localhost:5173")
public class LikeController {

  @Autowired
  private LikeRepository likeRepository;

  @GetMapping
  public List<LikeDTO> getAllLikes() {
    List<LikeModel> likes = (List<LikeModel>) likeRepository.findAll();
    return likes.stream().map(this::convertToDTO).collect(Collectors.toList());
  }


  @GetMapping("/{id}")
  public LikeDTO getLikeById(@PathVariable Long id) {
      LikeModel likeModel = likeRepository.findById(id).orElse(null);
      return likeModel != null ? convertToDTO(likeModel) : null;
    }

    @PostMapping
    public LikeDTO createLike(@RequestBody LikeDTO likeDTO) {
      LikeModel likeModel = new LikeModel();
      likeModel.setLikeId(likeDTO.getLikeId());
      likeModel.setCount(likeDTO.getCount());
      LikeModel savedLike = likeRepository.save(likeModel);
      return convertToDTO(savedLike);
    }

    @PutMapping("/{id}")
    public LikeDTO incrementLike(@PathVariable Long id, @RequestBody LikeDTO likeDTO) {
      Optional<LikeModel> savedLike = likeRepository.findById(id);
      if (savedLike.isPresent()) {
        LikeModel likeModel = savedLike.get();
        likeModel.setCount(likeDTO.getCount() + 1);
        LikeModel updatedLike = likeRepository.save(likeModel);
        return convertToDTO(updatedLike);
      }
      return null;
      }

    private LikeDTO convertToDTO(LikeModel likeModel) {
      LikeDTO likeDTO = new LikeDTO();
      likeDTO.setLikeId(likeModel.getLikeId());
      likeDTO.setCount(likeModel.getCount());
      return likeDTO;
  }
}






