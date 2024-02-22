package com.todolistcore.ToDoListCore.dto;

import java.util.Set;

import com.todolistcore.ToDoListCore.model.TaskPriority;
import com.todolistcore.ToDoListCore.model.TaskStatus;

import jakarta.validation.constraints.NotNull;

public record TaskDTO(
    @NotNull String title,
    @NotNull String description,
    @NotNull String finalDate,
    @NotNull TaskStatus status,
    @NotNull TaskPriority priority,
    @NotNull long user_id
) {
    
}
