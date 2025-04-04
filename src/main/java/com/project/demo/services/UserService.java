package com.project.demo.services;

import com.project.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements ModelUserService {
    Map<Long, User> data = new HashMap<Long, User>();

    public User getUser(long idUser){
        return data.get(idUser);
    }

    public void putUser(User user){
        data.put(user.getIdUser(), user);
    }

}
