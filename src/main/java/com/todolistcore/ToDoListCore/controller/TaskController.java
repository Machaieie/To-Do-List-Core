package com.todolistcore.ToDoListCore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolistcore.ToDoListCore.dto.TaskDTO;
import com.todolistcore.ToDoListCore.model.Task;
import com.todolistcore.ToDoListCore.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllPromotions() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("task")
    public ResponseEntity addTask(@Valid @RequestBody TaskDTO taskDTO){
        try {
           Task task = taskService.addTask(taskDTO);
           return ResponseEntity.status(HttpStatus.CREATED).body(task);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/updateTask/{id}")
    public ResponseEntity<Object> updateTask (@PathVariable(value = "id") long id, @Valid @RequestBody TaskDTO taskDTO){
        try {
            Task updateTask = taskService.updateTask(id, taskDTO);
            return ResponseEntity.ok().body(updateTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Object> deleteTask(@Valid @PathVariable(value = "id") long id){
        try {
             taskService.deleteTask(id);
             return ResponseEntity.status(HttpStatus.OK).body("Task deleted succesfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
