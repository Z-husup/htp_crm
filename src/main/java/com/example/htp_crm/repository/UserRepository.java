package com.example.htp_crm.repository;

import com.example.htp_crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByUsername(String username);
}