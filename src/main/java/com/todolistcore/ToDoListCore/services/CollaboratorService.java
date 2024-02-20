package com.todolistcore.ToDoListCore.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolistcore.ToDoListCore.dto.CollaboratorDTO;
import com.todolistcore.ToDoListCore.exceptions.ResourceNotFoundException;
import com.todolistcore.ToDoListCore.model.Collaborator;
import com.todolistcore.ToDoListCore.model.Task;
import com.todolistcore.ToDoListCore.model.User;
import com.todolistcore.ToDoListCore.repository.CollaboratorRepository;
import com.todolistcore.ToDoListCore.repository.TaskRepository;
import com.todolistcore.ToDoListCore.repository.UserRepository;

@Service
public class CollaboratorService {

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Collaborator addCollaborator(CollaboratorDTO collaboratorDTO) throws Exception{
        User user = userRepository.findById(collaboratorDTO.user_id()).orElseThrow(() -> new ResourceNotFoundException("User with ID:: " + collaboratorDTO.user_id() + " not found"));
        Task task = taskRepository.findById(collaboratorDTO.task_id()).orElseThrow(() -> new ResourceNotFoundException("Task with ID:: " + collaboratorDTO.task_id() + " not found"));
        Collaborator collaborator = new Collaborator();
        collaborator.setUser(user);
        //collaborator.setTasks();
        

        return collaboratorRepository.save(collaborator);
    }
    
}
