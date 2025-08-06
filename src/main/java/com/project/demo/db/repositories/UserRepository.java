package com.project.demo.db.repositories;

public interface UserRepository {
    boolean existsByUserId(Long userId);
    void insert(Long userid, String userName);
}
