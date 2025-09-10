package com.project.demo.services;

import com.project.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements ModelUserService {
    Map<Long, User> users = new HashMap<Long, User>();

    public boolean existUser(long idUser){
        return users.containsKey(idUser);
    }

    public void insertUser(long idUser, String nameUser){
        User user = new User(idUser, nameUser);
        users.put(user.getIdUser(), user);
    }

}
