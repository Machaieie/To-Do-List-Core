package com.todolistcore.ToDoListCore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolistcore.ToDoListCore.model.Collaborator;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {
    
}
