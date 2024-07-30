package com.example.population.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Class which represents user in database
 */
@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    /**
     * User's id, it is automatically incremented
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;
    private String password;
    private String email;
    private Date joined;
    private Boolean is_admin;

    /**
     * User class constructor, which allows to create User's entity as Java object using Hibernate and save it into database
     * @param login
     * @param password
     * @param email
     * @param joined
     * @param is_admin
     */
    public User(String login, String password, String email, Date joined, Boolean is_admin) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.joined = joined;
        this.is_admin = is_admin;
    }
}
