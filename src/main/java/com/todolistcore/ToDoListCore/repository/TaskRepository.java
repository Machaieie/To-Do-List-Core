package com.todolistcore.ToDoListCore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolistcore.ToDoListCore.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    Boolean existsByTitle(String title);
}
