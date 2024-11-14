package com.kaos.musa.repositories;

import com.kaos.musa.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    UserDetails findByUsername(String username);

    boolean existsByUsername(String username);
}
