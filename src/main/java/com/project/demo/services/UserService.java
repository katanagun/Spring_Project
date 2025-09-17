package com.project.demo.services;

import com.project.demo.db.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ModelUserService {
    private final UserRepository userDbRepository;

    public UserService(UserRepository userDbRepository){
        this.userDbRepository = userDbRepository;
    }

    public boolean existUser(Long userId){
        return userDbRepository.existsByUserId(userId);
    }

    public void insertUser(Long userId, String userName){
        userDbRepository.insert(userId, userName);
    }

}
