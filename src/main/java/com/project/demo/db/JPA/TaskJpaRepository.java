package com.project.demo.db.JPA;

import com.project.demo.db.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Profile("JPA")
@Repository
public interface TaskJpaRepository extends JpaRepository<Task, Long> {

}
