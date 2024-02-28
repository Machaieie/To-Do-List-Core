package com.todolistcore.ToDoListCore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolistcore.ToDoListCore.dto.SignUpDTO;
import com.todolistcore.ToDoListCore.model.User;
import com.todolistcore.ToDoListCore.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class AuthenticationController {
    
    @Autowired
    private UserService userService;

    
}
