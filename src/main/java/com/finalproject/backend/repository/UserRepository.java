package com.finalproject.backend.repository;

import com.finalproject.backend.models.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);
}
