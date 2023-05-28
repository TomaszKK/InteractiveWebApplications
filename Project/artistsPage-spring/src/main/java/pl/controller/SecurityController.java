package pl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

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
