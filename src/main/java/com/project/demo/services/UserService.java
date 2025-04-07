package com.project.demo.services;

import com.project.demo.db.repository.UserRep;
import com.project.demo.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements ModelUserService {
    @Autowired
    private UserRep userRep;

    public User getUser(long idUser){
        Optional<User> optionalUser = userRep.findById(idUser);
        return optionalUser.orElse(null);
    }

    public void putUser(User user){
        userRep.save(user);
    }

}
