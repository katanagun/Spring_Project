package com.project.demo.services;

import com.project.demo.db.User;

public interface ModelUserService {
    public User getUser(long key);
    public void putUser(User user);
}
