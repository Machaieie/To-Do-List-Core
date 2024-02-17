package com.todolistcore.ToDoListCore.dto;

import jakarta.validation.constraints.NotNull;

public record CollaboratorDTO(
    @NotNull long user_id,
    @NotNull long task_id
) {
    
}
