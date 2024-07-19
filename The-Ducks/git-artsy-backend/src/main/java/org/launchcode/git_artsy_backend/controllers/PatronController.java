package org.launchcode.git_artsy_backend.controllers;

import org.launchcode.git_artsy_backend.repositories.PatronRepository;
import org.launchcode.git_artsy_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PatronController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatronRepository patronRepository;
}
