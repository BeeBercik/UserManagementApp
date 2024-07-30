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
     * This method checks whether user is logged in. It decides about the main page look.
     * @param model we add data to the model to show them in the view (thymeleaf templates)
     * @param session object represents Session
     * @return returns template's name - redirects to admin page (ff the logged user is admin) or main page (if user is not logged or logged as ordinary user)
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
     * Method responsible for user logout, it invalidates the session
     * @param session
     * @return returns 'main' template
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // uniewaznij sesje

        return "main";
    }

}
