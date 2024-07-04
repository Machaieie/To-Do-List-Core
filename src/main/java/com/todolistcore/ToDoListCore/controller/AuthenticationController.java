package com.todolistcore.ToDoListCore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolistcore.ToDoListCore.dto.RegistrationRequestDTO;
import com.todolistcore.ToDoListCore.model.AuthenticationResponse;
import com.todolistcore.ToDoListCore.model.Response;
import com.todolistcore.ToDoListCore.model.User;
import com.todolistcore.ToDoListCore.services.AuthenticationService;


@RestController 
@RequestMapping("api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {
     @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequestDTO usuario){
        return ResponseEntity.ok(authenticationService.register(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login( @RequestBody User usuario){
        return ResponseEntity.ok(authenticationService.authenticate(usuario));
    }
}