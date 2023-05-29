package pl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/security")
public class SecurityController {
    @GetMapping("/visitor")
    @PreAuthorize("hasRole('VISITOR') or hasRole('ARTIST') or hasRole('ADMIN')")
    public String visitorAccess() {
        return ">>> Visitor Contents!";
    }


    @GetMapping("/artist")
    @PreAuthorize("hasRole('ARTIST') or hasRole('ADMIN')")
    public String artistAccess() {
        return ">>> Artist Contents!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return ">>> Admin Contents";
    }
}
