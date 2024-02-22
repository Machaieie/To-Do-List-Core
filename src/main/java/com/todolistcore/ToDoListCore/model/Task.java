package com.todolistcore.ToDoListCore.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "Task")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private String createdDate;
    private String finalDate;
    private TaskStatus status;
    private TaskPriority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Collaborator> collaborators;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

   

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Collaborator> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<Collaborator> collaborators) {
        this.collaborators = collaborators;
    }

   
    

}
