package com.example.population.repository;

import com.example.population.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository {
    User findByLogin(String login);
}
