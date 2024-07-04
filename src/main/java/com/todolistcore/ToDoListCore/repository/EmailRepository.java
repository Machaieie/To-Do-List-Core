package com.todolistcore.ToDoListCore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todolistcore.ToDoListCore.model.EmailDetails;

@Repository
public interface EmailRepository extends JpaRepository <EmailDetails, Long>{
    
}
