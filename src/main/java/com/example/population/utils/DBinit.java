package com.example.population.utils;

import com.example.population.model.User;
import com.example.population.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class DBinit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        List<User> initUsers = new ArrayList<>(List.of(
                new User("mikolaj", "mikolaj123", "miki123@gmail.com", new Date(), false),
                new User("piklok", "piklok123", "piklok@gmail.com",new Date(), false),
                new User("rokan123", "rokan123", "rokan123@gmail.com",new Date(), true)
        ));

        userRepository.saveAll(initUsers);
    }
}
