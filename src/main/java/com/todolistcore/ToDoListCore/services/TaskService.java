package com.todolistcore.ToDoListCore.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.todolistcore.ToDoListCore.dto.TaskDTO;
import com.todolistcore.ToDoListCore.exceptions.EmptyDatabaseException;
import com.todolistcore.ToDoListCore.exceptions.ResourceNotFoundException;
import com.todolistcore.ToDoListCore.model.Collaborator;
import com.todolistcore.ToDoListCore.model.Task;
import com.todolistcore.ToDoListCore.model.User;
import com.todolistcore.ToDoListCore.repository.CollaboratorRepository;
import com.todolistcore.ToDoListCore.repository.TaskRepository;
import com.todolistcore.ToDoListCore.repository.UserRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    public Task addTask(TaskDTO taskDTO) throws Exception{
        if(taskRepository.existsByTitle(taskDTO.title())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ja existe uma Tarefa com o título indicado");
        }

        User user = userRepository.findById(taskDTO.user_id()).orElseThrow(  () -> new ResourceNotFoundException("User with ID:: " + taskDTO.user_id() + " not found"));
        Task task = new Task();
        task.setTittle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setCreatedDate(getCurrentDateTime());
        task.setFinalDate(taskDTO.finalDate());
        task.setPriority(taskDTO.priority());
        task.setStatus(taskDTO.status());
        Set<Collaborator> colaborates = new HashSet<>();
        for (Long collaboraId  : taskDTO.colaborates()) {
            Collaborator collaborator = collaboratorRepository.findById(collaboraId).orElseThrow(() -> new ResourceNotFoundException("Collaborator with ID: " + collaboraId + " not found!"));
            colaborates.add(collaborator);
        }
        task.setCollaborators(colaborates);
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task updateTask(long id, TaskDTO taskDTO) throws ResourceNotFoundException{
        Task existingTask = taskRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Task with ID: " + id + " not found!"));
        existingTask.setTittle(taskDTO.title());
        existingTask.setDescription(taskDTO.description());
        existingTask.setFinalDate(taskDTO.finalDate());
        existingTask.setPriority(taskDTO.priority());
        existingTask.setStatus(taskDTO.status());
        return taskRepository.save(existingTask);
    }

    
    public void deleteTask(long id) throws ResourceNotFoundException{
        Task task = taskRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Task with ID: " + id + " not found!"));
        taskRepository.delete(task);
    }

    public List<Task> getAllTasks() throws EmptyDatabaseException{
        List<Task> tasks = taskRepository.findAll();
        if(tasks.isEmpty()){
            throw new EmptyDatabaseException("No tasks at database");
        }
        return tasks;
    }

    



     private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return now.format(formatter);
    }
    
}
