package com.todolistcore.ToDoListCore.model;


import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String name;
    private String username;
    private String email;
    private Set<UserRole> roles;
    private String token;
}