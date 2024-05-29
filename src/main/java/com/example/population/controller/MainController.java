package com.example.population.controller;

import com.example.population.model.User;
import com.example.population.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/")
    public String main(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if(user != null && user.getIs_admin()) return "redirect:/admin";

        List<User> users = userRepository.findAll();
        User loggedUser = (User) session.getAttribute("loggedUser");

        model.addAttribute("users", users);
        model.addAttribute("loggedUser", loggedUser);

        return "main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // uniewaznij sesje

        return "main";
    }

}
