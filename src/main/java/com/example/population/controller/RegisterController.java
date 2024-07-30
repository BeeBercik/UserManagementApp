package com.example.population.controller;

import com.example.population.model.User;
import com.example.population.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is responsible for user registration. It checks whether all conditions are met.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    private ArrayList<String> errors;

    @Autowired
    private UserRepository userRepository;

    /**
     * Method returns the name of template that will be displayed
     * @param model
     * @return
     */
    @GetMapping
    public String register(Model model) {
        return "register";
    }

    /**
     * Method adds a User to the database if all conditions are met and data is correctly.
     * @param login
     * @param password
     * @param r_password
     * @param email
     * @param model
     * @return If user is added successfully it redirect user to login page by returning url. If user i not added, it returns register template name
     */
    @PostMapping
    private String addUser(@RequestParam(name = "login") String login,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name = "r_password") String r_password,
                         @RequestParam(name = "email") String email,
                           Model model) {
        boolean ok = true;
        this.errors = new ArrayList<>(); // inny sposob PLS

        if(login.isEmpty() || password.isEmpty() ||
            r_password.isEmpty() || email.isEmpty()) {
            this.errors.add("Podaj wymagane dane.");
            ok = false;
        }
        if(!password.equals(r_password)) {
            this.errors.add("Podane hasla nie sa identyczne.");
            ok = false;
        }
        if(!this.isLoginAvailable(login)) {
            this.errors.add("Podana nazwa juz zajeta.");
            ok = false;
        }
        if(!this.isEmailAvailable(email)) {
            this.errors.add("Podany email istnieje juz w bazie.");
            ok = false;
        }

        String url;
        if(ok) {
            User user = new User(login, password, email, new Date(), false);
            userRepository.save(user);
            url = "redirect:/login";
        } else {
            model.addAttribute("errors", this.errors);
            url = "register";
        }

        return url;
    }

    private boolean isLoginAvailable(String login) {
        boolean isFree = true;

        List<User> users = userRepository.findAll();
        for(User user : users) {
            if (user.getLogin().equals(login)) {
                isFree = false;
                break;
            }
        }

        return isFree;
    }

    private boolean isEmailAvailable(String email) {
        boolean isFree = true;

        List<User> users = userRepository.findAll();
        for(User user : users) {
            if (user.getLogin().equals(email)) {
                isFree = false;
                break;
            }
        }

        return isFree;
    }

}
