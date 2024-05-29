package com.example.population.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;
    private String password;
    private String email;
    private Date joined;
    private Boolean is_admin;

    public User(String login, String password, String email, Date joined, Boolean is_admin) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.joined = joined;
        this.is_admin = is_admin;
    }
}
