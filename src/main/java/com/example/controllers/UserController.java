package com.example.controllers;

import com.example.models.User;
import com.example.repositories.UserRepository;
import com.example.security.PasswordUtils;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String usersPost(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/addUser")
    public String usersGet(Model model) {
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUserPost(@NotBlank(message = "Empty firstName") @RequestParam String firstName,
                              @NotBlank(message = "Empty lastName") @RequestParam String lastName,
                              @NotBlank(message = "Empty email") @RequestParam String email,
                              @NotBlank(message = "Empty password") @RequestParam String password, Model model) {
        String status = "User";
        String salt = PasswordUtils.generateSalt();
        String passhash = PasswordUtils.generateHash(password + ":" + salt);
        User user = new User(firstName, lastName, email, status, passhash, salt);
        userRepository.save(user);


        return "redirect:/";
    }

}
