package com.example.population.repository;

import com.example.population.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

/**
 * Implementation of the CustomUserRepository interface.
 * Provides custom methods to interact with te User entity
 */
public class CustomUserRepoImpl implements CustomUserRepository {
    /**
     * The EntityManager is used to interact with the persistence context.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds user by its login
     * @param login login of the user to find
     * @return returns User object from database or null if User is ot found
     */
    @Override
    public User findByLogin(String login) {
        String hql = "FROM User WHERE login = :login";
        Query query = entityManager.createQuery(hql);
        query.setParameter("login", login);

        List<User> result = query.getResultList();

        if(result.isEmpty()) return null;

        return result.get(0);
    }
}
