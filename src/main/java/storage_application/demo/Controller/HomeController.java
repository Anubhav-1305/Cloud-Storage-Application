package storage_application.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import storage_application.demo.Service.CredentialService;
import storage_application.demo.Service.FileService;
import storage_application.demo.Service.NoteService;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String homeView(Model model, Principal principal) {
        model.addAttribute("files", fileService.getFiles(principal));
        model.addAttribute("notes", noteService.getNotes(principal));
        model.addAttribute("credentials", credentialService.getCredentials(principal));
        return "home";
    }
}