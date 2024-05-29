package com.example.population.repository;

import com.example.population.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;


public class CustomUserRepoImpl implements CustomUserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByLogin(String login) {
        String hql = "FROM User WHERE login = :login";
        Query query = entityManager.createQuery(hql);
        query.setParameter("login", login);

        List<User> result = query.getResultList();

        return result.get(0);
    }
}
