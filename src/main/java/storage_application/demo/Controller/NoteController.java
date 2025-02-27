package storage_application.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import storage_application.demo.Model.Note;
import storage_application.demo.Service.NoteService;

import java.security.Principal;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/note")
    public String createNote(Note note, Principal principal, Model model) {
        try {
            noteService.createNote(note, principal);
            model.addAttribute("success", true);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Error creating note: " + e.getMessage());
            return "result";
        }
    }

    @GetMapping("/note/edit/{noteId}")
    public String editNote(@PathVariable Integer noteId, Model model) {
        Note note = noteService.getNote(noteId);
        model.addAttribute("note", note);
        return "note-edit";
    }

    @PostMapping("/note/edit")
    public String updateNote(Note note, Model model) {
        try {
            noteService.updateNote(note);
            model.addAttribute("success", true);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Error updating note: " + e.getMessage());
            return "result";
        }
    }

    @GetMapping("/note/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model) {
        try {
            noteService.deleteNote(noteId);
            model.addAttribute("success", true);
            return "result";
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Error deleting note: " + e.getMessage());
            return "result";
        }
    }
}