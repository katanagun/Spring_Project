package com.project.demo.db.JPA;

import com.project.demo.db.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Profile("JPA")
@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(Long userId);
}
