package com.cagri.ecommerce.cagristore.controller;

import com.cagri.ecommerce.cagristore.dto.LoginRequest;
import com.cagri.ecommerce.cagristore.entity.User;
import com.cagri.ecommerce.cagristore.exception.StoreException;
import com.cagri.ecommerce.cagristore.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authenticationService.register(
                user.getName(), user.getEmail(), user.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = authenticationService.authenticate(
                loginRequest.email(), loginRequest.password());

        if (isAuthenticated) {
            boolean isAdmin = authenticationService.isAdmin(loginRequest.email());
            if (isAdmin) {
                return ResponseEntity.ok().body("Admin login successful");
            } else {
                return ResponseEntity.ok().body("User login successful");
            }
        } else {
            return ResponseEntity.status(401).body("Wrong Password or Email");
        }
    }


    @GetMapping("/auth/{email}")
    public Long findUserByEmail(@PathVariable String email) {
        return authenticationService.findUserIdByEmail(email);

    }

//    @PostMapping("defaultAdmin")
//    public User registerDefaultAdmin() {
//        return authenticationService.registerDefaultAdmin();
//    }
//
//    @PostMapping("defaultUser")
//    public User registerDefaultUser() {
//        return authenticationService.registerDefaultUser();
//    }


}

