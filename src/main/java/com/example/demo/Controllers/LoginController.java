package com.example.demo.Controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginErrorView(Model model) {
        model.addAttribute("authenticationError", true);

        return "login";
    }

}