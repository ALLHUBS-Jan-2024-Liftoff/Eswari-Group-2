package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.repositories.ArtistRepository;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ArtistController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;
}
