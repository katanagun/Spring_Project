package com.project.demo.services;

import com.project.demo.models.User;

public interface ModelUserService {
    public User getUser(long key);
    public void putUser(User user);
}
