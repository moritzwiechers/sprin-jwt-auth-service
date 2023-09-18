package de.gemorra.springjwt.controller;

import de.gemorra.springjwt.SpringSecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/hello-world")
    public String hellowWorld() {
        return "Hello World";
    }

    @GetMapping(value = "/hello-user")
    public String helloUser() {
        return "Hello User " + SpringSecurityUtils.getUsername();
    }

    @GetMapping(value = "/hello-admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String helloAdmin() {
        return "Hello Admin " + SpringSecurityUtils.getUsername();
    }

}
