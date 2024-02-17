package com.todolistcore.ToDoListCore.services;

import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.todolistcore.ToDoListCore.dto.SignUpDTO;
import com.todolistcore.ToDoListCore.exceptions.EmptyDatabaseException;
import com.todolistcore.ToDoListCore.model.User;
import com.todolistcore.ToDoListCore.model.UserRole;
import com.todolistcore.ToDoListCore.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepository.findByUsernameAndEnabled(username, true)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User registerUser(SignUpDTO request) throws Exception {
        if (userRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ja existe usuario com nome indicado");
        }
         
        User user = new User();
        user.setUsername(request.username());
        user.setName(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        String token = UUID.randomUUID().toString();
        user.setConfirmationToken(token);
        user.addRole(new UserRole(request.role()));
        userRepository.save(user);
        return user;

    }

   public List<User> getAllUsers(){
         List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new EmptyDatabaseException("No authors found in the database.");
        }

        return users;
    }
}
