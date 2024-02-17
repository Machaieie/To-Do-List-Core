package com.todolistcore.ToDoListCore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolistcore.ToDoListCore.model.EmailDetails;

public interface EmailRepository extends JpaRepository <EmailDetails, Long>{
    
}
