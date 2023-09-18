package de.gemorra.springjwt.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            return new User("user", "$2a$10$3wWGk8BHOKxtfihtzfJHbe6ur9ClGGEHywYaT6V61409sbWQ18BPy",
                    List.of());
        } else if ("admin".equals(username)) {
            return new User("admin", "$2a$10$3wWGk8BHOKxtfihtzfJHbe6ur9ClGGEHywYaT6V61409sbWQ18BPy",
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}