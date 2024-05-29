package com.example.population.repository;

import com.example.population.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> , CustomUserRepository {
    //    User findByLogin(String login); // jpa samo tworzy cialo/implement.
}
