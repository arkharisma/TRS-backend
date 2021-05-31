package com.finalproject.backend.repository;

import java.util.Optional;

import com.finalproject.backend.models.user.ERole;
import com.finalproject.backend.models.user.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
