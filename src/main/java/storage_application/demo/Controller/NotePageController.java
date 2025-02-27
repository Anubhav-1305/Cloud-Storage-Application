package storage_application.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import storage_application.demo.Service.NoteService;
import java.security.Principal;

@Controller
public class NotePageController {

    private final NoteService noteService;

    public NotePageController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String notesPage(Model model, Principal principal) {
        model.addAttribute("notes", noteService.getNotes(principal));
        return "notes";
    }
}