package com.example.Disaster.Management.Repositories;

import com.example.Disaster.Management.Tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);

    // Check if a user exists by email using a custom JPQL query
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
    boolean existsByEmail(String email);

    // Get a user by ID using a custom JPQL query
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findById(Long id);

    // Get all users (using the default method if you want, or you can customize it)
    @Query("SELECT u FROM User u")
    Iterable<User> findAllUsers();
}
