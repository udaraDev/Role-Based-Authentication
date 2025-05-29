package com.project.rolebasedauthentication.repository;

import com.project.rolebasedauthentication.entities.Role;
import com.project.rolebasedauthentication.entities.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
