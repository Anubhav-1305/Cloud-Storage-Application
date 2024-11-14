package com.example.demo.Controllers;

import com.example.demo.Service.CredentialService;
import com.example.demo.Service.UserService;
import com.example.demo.Service.FileService;
import com.example.demo.Service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService users;
    private final NoteService notes;
    private final FileService files;
    private final CredentialService credentials;

    public HomeController(
            UserService users,
            NoteService notes,
            FileService files,
            CredentialService credentials
    ) {
        this.users = users;
        this.notes = notes;
        this.files = files;
        this.credentials = credentials;
    }

    @GetMapping()
    public String homeView(Authentication authentication, Model model) {

        try {
            var userId = users.getUser(
                            authentication.getName()
                    ).getUserId()
                    .toString();

            model.addAttribute("notes", notes.allBy(userId));
            model.addAttribute("files", files.allBy(userId));
            model.addAttribute("credentials", credentials.allBy(userId));

        } catch (Exception ignored) {
            return "redirect:/logout-success";
        }

        return "home";
    }

}