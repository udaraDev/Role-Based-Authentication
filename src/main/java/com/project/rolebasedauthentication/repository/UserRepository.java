package com.project.rolebasedauthentication.repository;

import com.project.rolebasedauthentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find a user by their username
     * 
     * @param username the username to search for
     * @return the user wrapped in an Optional
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Check if a username already exists
     * 
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    Boolean existsByUsername(String username);
    
    /**
     * Check if an email already exists
     * 
     * @param email the email to check
     * @return true if the email exists, false otherwise
     */
    Boolean existsByEmail(String email);
}