package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/admin")
    public String showAdminPage() {
        return "Admin";
    }

    @GetMapping("/user")
    public String userInfo() {
        return "User";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "Login";
    }
}
