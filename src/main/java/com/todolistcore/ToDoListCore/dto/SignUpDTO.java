package com.todolistcore.ToDoListCore.dto;

import com.todolistcore.ToDoListCore.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignUpDTO(
    @NotBlank String name,
    @NotBlank String username,
    @NotBlank String password,
    @NotBlank @Email String email,
    @NotNull Role role
) {
    
}
