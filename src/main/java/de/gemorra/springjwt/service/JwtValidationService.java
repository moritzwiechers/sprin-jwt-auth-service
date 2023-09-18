package de.gemorra.springjwt.service;

import de.gemorra.springjwt.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtValidationService {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;


    public UserDetails getUserDetailsIfValid(String requestTokenHeader) {
        String username = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtils.getUserNameFromJwtToken(jwtToken);
            } catch (IllegalArgumentException e) {
                log.warn("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                log.warn("JWT Token has expired");
            }
        } else {
            log.warn("JWT Token does not begin with Bearer String");
        }

        if (username != null) {
            return this.jwtUserDetailsService.loadUserByUsername(username);
        }
        return null;
    }
}
