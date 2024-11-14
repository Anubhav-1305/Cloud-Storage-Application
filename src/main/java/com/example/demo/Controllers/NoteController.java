package com.example.demo.Controllers;

import com.example.demo.Model.Note;
import com.example.demo.Service.UserService;

import com.example.demo.Service.NoteService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final UserService users;
    private final NoteService notes;

    public NoteController(UserService userService, NoteService noteService) {
        this.users = userService;
        this.notes = noteService;
    }

    public List<String> validate(Map<String, String> data) {
        List<String> errors = new ArrayList<String>();

        if (data.get("noteTitle").isEmpty())
            errors.add("This field should not be empty.");

        if (data.get("noteDescription").isEmpty())
            errors.add("This field should not be empty.");

        return errors;
    }

    @PostMapping()
    public String createView(
            HttpServletResponse response,
            Authentication authentication,
            @RequestParam Map<String, String> data,
            Model model
    ) {
        var UID = users.getUser(authentication.getName()).getUserId();
        notes.add(
                new Note(
                        null,
                        data.get("noteTitle"),
                        data.get("noteDescription"),
                        UID
                )
        );

        var errors = validate(data);
        model.addAttribute("success", true);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("success", false);

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return "result";
    }

    @PutMapping(value = "/{noteId}")
    public String editView(
            HttpServletResponse response,
            Authentication authentication,
            @RequestParam Map<String, String> data,
            Model model
    ) {
        var UID = users.getUser(authentication.getName()).getUserId();
        notes.add(
                new Note(
                        Integer.parseInt(data.get("noteId")),
                        data.get("noteTitle"),
                        data.get("noteDescription"),
                        UID
                )
        );

        var errors = validate(data);
        model.addAttribute("success", true);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("success", false);

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return "result";
    }

    @DeleteMapping(value = "/{noteId}")
    public String removeView(
            HttpServletResponse response,
            @PathVariable Integer noteId,
            Authentication authentication,
            Model model
    ) {
        List<String> errors = new ArrayList<String>();
        model.addAttribute("success", true);
        try {
            var UID = users.getUser(authentication.getName()).getUserId();
            notes.remove(new Note(noteId, UID));

        } catch (Exception ignore) {
            errors.add("The note was not removed.");
            model.addAttribute("errors", errors);
            model.addAttribute("success", false);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }

        return "result";
    }

}
