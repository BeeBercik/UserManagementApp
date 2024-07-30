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

/**
 *  Class responsible for User log in logic
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    /**
     * When user sends http request with ending /login this method display login template
     * @param model
     * @return returns name of the template
     */
    @GetMapping
    public String login(Model model) {
        return "login";
    }

    /**
     * Method checks whether login data are correct.
     * @param login user login from post request
     * @param password user password from post request
     * @param model
     * @param session
     * @return returns a string that represents the name of template or url to redirect user to the main page
     */
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
