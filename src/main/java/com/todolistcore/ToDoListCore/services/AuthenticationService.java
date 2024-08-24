package com.todolistcore.ToDoListCore.services;

import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todolistcore.ToDoListCore.dto.RegistrationRequestDTO;
import com.todolistcore.ToDoListCore.exceptions.DuplicatedEntityException;
import com.todolistcore.ToDoListCore.model.User;
import com.todolistcore.ToDoListCore.model.UserRole;
import com.todolistcore.ToDoListCore.repository.UserRepository;
import com.todolistcore.ToDoListCore.model.AuthenticationResponse;
import com.todolistcore.ToDoListCore.model.Response;
import com.todolistcore.ToDoListCore.model.Role;

@Service
public class AuthenticationService  {
    
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   private JwtService jwtService;

   @Autowired
   private AuthenticationManager authenticationManager;

   public AuthenticationResponse register(RegistrationRequestDTO request) {

      Optional<User> user = userRepository.findByUsername(request.username());
      if (user.isPresent()) {
         throw new DuplicatedEntityException(
               "User com o username de " + request.username() + " ja foi cadastrado no sistema");
      }
      User userdetails = new User();
      userdetails.setName(request.name());
      userdetails.setUsername(request.username());
      userdetails.setEmail(request.email());
      userdetails.setPassword(passwordEncoder.encode(request.password()));
      userdetails.addRole(new UserRole(Role.ADMIN));
      userdetails = userRepository.save(userdetails);
      String token = jwtService.generateToken(userdetails);
      return new AuthenticationResponse(token);
      
      
   }

   
   public Response authenticate(User request) {

      try {
         var usernamePassword = new UsernamePasswordAuthenticationToken(
               request.getUsername(),
               request.getPassword());

         var auth = this.authenticationManager.authenticate(usernamePassword);
         User User = userRepository.findByUsername(request.getUsername()).orElseThrow();
         String token = jwtService.generateToken(User);
         UserDetails userDetails = (UserDetails) auth.getPrincipal();
         Response response = new Response();
         Set<UserRole> roles = ((User) auth.getPrincipal()).getRole();
         response.setId(User.getId());
         response.setName(User.getName());
         response.setUsername(userDetails.getUsername());
         response.setEmail(User.getEmail());
         response.setRoles(roles);
         response.setToken(token);
         return response;
      } catch (BadCredentialsException e) {
         throw new BadCredentialsException("User ou senha invalido!");
      }
      

   }

   public List<Response> getAllUsers() {
    List<User> users = userRepository.findAll();

    List<Response> responseList = users.stream().map(user -> {
        Response response = new Response();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRoles(user.getRole());
        return response;
    }).collect(Collectors.toList());

    return responseList;
}
}
