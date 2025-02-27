package storage_application.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import storage_application.demo.Service.FileService;
import java.security.Principal;

@Controller
public class FilePageController {

    private final FileService fileService;

    public FilePageController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/files")
    public String filesPage(Model model, Principal principal) {
        model.addAttribute("files", fileService.getFiles(principal));
        return "files";
    }
}