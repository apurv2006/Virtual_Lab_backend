package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/submitData")
    public String submitData(@RequestBody User user) {
        userRepository.save(user);
        return "Data submitted successfully!";
    }

    @GetMapping("/submitData")
    public String submitData(@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "Data submitted successfully!";
    }
}
