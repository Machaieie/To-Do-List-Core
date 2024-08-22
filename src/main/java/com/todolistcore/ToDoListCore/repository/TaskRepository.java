package com.todolistcore.ToDoListCore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolistcore.ToDoListCore.model.Task;
import com.todolistcore.ToDoListCore.model.User;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    Boolean existsByTitle(String title);
    List<Task> findByUser(User user);
}
