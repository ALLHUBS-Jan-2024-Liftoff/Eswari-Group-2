package org.launchcode.git_artsy_backend.repositories;

import org.launchcode.git_artsy_backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
