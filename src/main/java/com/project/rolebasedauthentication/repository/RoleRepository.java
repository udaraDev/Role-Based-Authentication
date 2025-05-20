package com.project.rolebasedauthentication.repository;

import com.project.rolebasedauthentication.entity.ERole;
import com.project.rolebasedauthentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /**
     * Find a role by its name
     * 
     * @param name the role name to search for
     * @return the role wrapped in an Optional
     */
    Optional<Role> findByName(ERole name);
}