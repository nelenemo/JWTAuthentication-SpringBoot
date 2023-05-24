package com.example.security;

import com.example.security.dto.AuthenticationRequest;
import com.example.security.dto.AuthenticatonResponse;
import com.example.security.service.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/api/api")
public class Security {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtTokenUtil ;



    @Autowired
    MyUserDetailsService userDetailsService;

    @GetMapping("/")
    public String getHome() {
        return ("<h1> WELCOME <h2>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1> WELCOME User <h2>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1> WELCOME Admin<h2>");
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("incorrect username or password", e);
        }
        // now the method need to return jwt
        //for that jwt must be created and we have created the jwtutil
        //which takes the user details inorder to create a jwt out of it
        //so we need access to the user details when means user details need to be fetch from user details service
        //that load user by user name

        final UserDetails userDetails=userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt= jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticatonResponse(jwt));
    }
}