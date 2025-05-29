
package com.project.rolebasedauthentication.controllers;

import com.project.rolebasedauthentication.entity.User;
import com.project.rolebasedauthentication.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            User user = userService.findByEmail(email);

            if (user != null) {
                return ResponseEntity.ok(user);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }
}