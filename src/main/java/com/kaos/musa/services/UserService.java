package com.kaos.musa.services;

import com.kaos.musa.entities.User;
import com.kaos.musa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void register(User user){
        repository.save(user);
    }

    public UserDetails findByUsername (String username){
        return repository.findByUsername(username);
    }

    public boolean isUsernameAlreadyRegistered(String username){
        return repository.existsByUsername(username);
    }
}
