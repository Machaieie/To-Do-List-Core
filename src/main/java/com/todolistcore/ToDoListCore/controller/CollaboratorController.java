package com.todolistcore.ToDoListCore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolistcore.ToDoListCore.dto.CollaboratorDTO;
import com.todolistcore.ToDoListCore.model.Collaborator;
import com.todolistcore.ToDoListCore.services.CollaboratorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;

    @GetMapping("/collaboators")
    public ResponseEntity <List<Collaborator>> getAllCollaborators (){
        List<Collaborator> collaborators = collaboratorService.getAllCollaborators();
        return new ResponseEntity<>(collaborators, HttpStatus.OK);
    }

    @PostMapping("/collaborator")
    public ResponseEntity addCollaborator (@Valid @RequestBody CollaboratorDTO collaboratorDTO){
        try {
            Collaborator collaborator = collaboratorService.addCollaborator(collaboratorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(collaborator.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteCollaborator/{id}")
    public ResponseEntity<Object> deleteCollaborator (@Valid @PathVariable(value = "id") long id){
        try {
            collaboratorService.deleteCollaborator(id);
            return ResponseEntity.status(HttpStatus.OK).body("Collaborator deleted succesfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    
}
