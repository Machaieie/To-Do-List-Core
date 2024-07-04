package com.todolistcore.ToDoListCore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.todolistcore.ToDoListCore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	public Optional<User> findByUsernameAndPassword(String username, String password);
	Optional<User>  findByUsername(String username);
	Boolean existsByUsername(String username);
	
	public List<User> findBynameContainingIgnoreCase(String name);
    
}
