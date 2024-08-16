package com.auth.service.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.service.app.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
