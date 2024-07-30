package com.example.population.controller;

import com.example.population.model.User;
import com.example.population.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * The main controller which is responsible for navigation on the main (start) website of the application
 */
@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    /**
     * This method checks weather user is logged in. It decides about the main page look.
     * If the logged user is admin, it redirects user to /admin page where ale users are listed and admin can make interaction with them.
     * If the user is not logged it redirects him to the main page where there will be two links - to log in or register (thymeleaf template main.html)
     * If the user is logged but not as admin it also redirects him to the main page but he will see all listed users (we make it in thymeleaf template main.html)
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/")
    public String main(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if(user != null && user.getIs_admin()) return "redirect:/admin";

        model.addAttribute("loggedUser", user);
        if(user == null) return "main";

        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "main";
    }

    /**
     * Method responsible for user logout, it also redirects user to the main page
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // uniewaznij sesje

        return "main";
    }

}
