package com.db.tasktracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.tasktracker.Model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}