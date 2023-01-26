package com.example.quicksend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);
    boolean existsUserById(Long id);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(Long id);
}
