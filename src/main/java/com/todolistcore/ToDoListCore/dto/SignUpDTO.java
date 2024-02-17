package com.todolistcore.ToDoListCore.dto;

import com.todolistcore.ToDoListCore.model.Role;

import jakarta.validation.constraints.NotBlank;

public record SignUpDTO(
    @NotBlank String name,
    @NotBlank String username,
    @NotBlank String password,
    Role role
) {
    
}
