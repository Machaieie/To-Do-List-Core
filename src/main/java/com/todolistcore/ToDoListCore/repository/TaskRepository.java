package com.todolistcore.ToDoListCore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolistcore.ToDoListCore.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{
    Boolean existsByTitle(String title);
}
