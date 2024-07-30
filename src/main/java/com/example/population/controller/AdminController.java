package com.example.population.controller;

import com.example.population.model.User;
import com.example.population.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

/**
 * Class represents admins' controller. Provides some admin actions.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Method checks if admin is logged in and display its panel
     * @param model
     * @param session
     * @return returns templates name
     */
    @GetMapping
    public String adminPanel(Model model, HttpSession session) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        User user = (User) session.getAttribute("loggedUser");
        if(user == null || !user.getIs_admin()) return "redirect:/";
        model.addAttribute("loggedUser", user);

        return "admin/adminPanel";
    }

    /**
     * Function remvoes user from database
     * @param id
     * @return url that redirect to admin page
     */
    @GetMapping("/remove/{userid}")
    private String removeUser(@PathVariable(name = "userid") Integer id) {
        userRepository.deleteById(id);

        return "redirect:/admin";
    }

    /**
     * Shows user details. Gets id from GET url and looks up user in database
     * @param id user id in GET url
     * @param model
     * @param session
     * @return return view (template) name with user details
     */
    @GetMapping("/userDetails/{userid}")
    private String showUserDetails(@PathVariable(name = "userid") Integer id,
                                   Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        if(loggedUser == null || !loggedUser.getIs_admin()) return "redirect:/";

        Optional<User> opuser = userRepository.findById(id);
        if(opuser.isPresent()) model.addAttribute("selectedUser", opuser.get());
        else return "redirect:/admin";

        return "admin/userDetails";
    }

}
