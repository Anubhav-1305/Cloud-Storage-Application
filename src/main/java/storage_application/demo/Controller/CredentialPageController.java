package storage_application.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import storage_application.demo.Service.CredentialService;
import java.security.Principal;

@Controller
public class CredentialPageController {

    private final CredentialService credentialService;

    public CredentialPageController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping("/credentials")
    public String credentialsPage(Model model, Principal principal) {
        model.addAttribute("credentials", credentialService.getCredentials(principal));
        return "credentials";
    }
}