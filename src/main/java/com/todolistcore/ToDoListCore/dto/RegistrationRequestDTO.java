package com.todolistcore.ToDoListCore.dto;

import com.todolistcore.ToDoListCore.model.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrationRequestDTO(
    @NotBlank String name,
    @NotBlank String username,
    @NotBlank String password,
    @NotBlank String email,
    @NotNull Role role
) {
    
}