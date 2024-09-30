package com.user_mangement.User_Mangement.repository;

import com.user_mangement.User_Mangement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
}
