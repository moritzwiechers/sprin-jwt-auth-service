package de.gemorra.springjwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping(value = "/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String hellowWorld() {
        return "hello Admin";
    }
}
