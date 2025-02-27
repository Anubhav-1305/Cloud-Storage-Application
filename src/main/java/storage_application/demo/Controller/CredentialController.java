package storage_application.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import storage_application.demo.Model.Credential;
import storage_application.demo.Service.CredentialService;

import java.security.Principal;

@Controller
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/credential")
    public String createCredential(Credential credential, Principal principal, Model model) {
        credentialService.createCredential(credential, principal);
        model.addAttribute("success", true);
        return "result";
    }

    @GetMapping("/credential/view/{credentialId}")
    public String viewCredential(@PathVariable Integer credentialId, Model model) {
        model.addAttribute("credential", credentialService.getCredential(credentialId));
        return "credential-edit";
    }

    @PostMapping("/credential/edit")
    public String updateCredential(Credential credential, Model model) {
        credentialService.updateCredential(credential);
        model.addAttribute("success", true);
        return "result";
    }

    @GetMapping("/credential/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId, Model model) {
        credentialService.deleteCredential(credentialId);
        model.addAttribute("success", true);
        return "result";
    }
}