package com.project.demo.services;

import com.project.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements ModelUserService {
    Map<Long, User> users = new HashMap<Long, User>();

    public boolean getUser(long idUser){
        return users.containsKey(idUser);
    }

    public void putUser(User user){
        users.put(user.getIdUser(), user);
    }

}