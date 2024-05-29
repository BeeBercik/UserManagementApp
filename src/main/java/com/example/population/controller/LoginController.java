package com.example.population.controller;

import com.example.population.model.User;
import com.example.population.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String login(Model model) {
        return "login";
    }

    @PostMapping
    public String signIn(@RequestParam(name = "login") String login,
                         @RequestParam(name = "password") String password,
                         Model model, HttpSession session) {
        boolean ok = true;
        User user = userRepository.findByLogin(login);

        if(login.isEmpty() || password.isEmpty()) ok = false;
        if(user == null) ok = false;
        if(user != null && !user.getPassword().equals(password)) ok = false;

        String url;
        if(ok) {
            session.setAttribute("loggedUser", user);
            url = "redirect:/";
        }
        else {
            url = "login";
            model.addAttribute("error", "Niepoprawne dane logowania.");
        }

        return url;
    }

}
