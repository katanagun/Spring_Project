package com.project.demo.db.InMemory;

import com.project.demo.db.User;
import com.project.demo.db.repositories.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Profile("InMemory")
public class UserInMemoryRepository implements UserRepository {
    public Map<Long, User> users = new HashMap<>();

    @Override
    public boolean existsByUserId(Long idUser){
        return users.containsKey(idUser);
    }

    @Override
    public void insert(Long idUser, String userName){
        User user = new User(idUser, userName);
        users.put(user.getUserId(), user);
    }

}