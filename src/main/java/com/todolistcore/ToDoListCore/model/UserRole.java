package com.todolistcore.ToDoListCore.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserRole implements Serializable{
    private static final long serialVersionUID = -8708550770042665050L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

    @Enumerated(EnumType.STRING)
	private Role role;



     
    public UserRole() {
    }

    public UserRole(Role role) {
        this.role = role;
    }

}
