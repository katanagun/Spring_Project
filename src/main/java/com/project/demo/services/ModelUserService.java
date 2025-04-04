package com.project.demo.services;

import com.project.demo.db.User;

public interface ModelUserService {
    public boolean existUser(Long userId);
    public void insertUser(Long userId, String userName);
}