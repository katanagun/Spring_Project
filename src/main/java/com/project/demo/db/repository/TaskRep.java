package com.project.demo.db.repository;

import com.project.demo.db.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRep extends JpaRepository<Task, Long> {
}
