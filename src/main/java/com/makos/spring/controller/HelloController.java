package com.makos.spring.controller;

import com.makos.spring.security.SecurePersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurePersonDetails personDetails = (SecurePersonDetails) authentication.getPrincipal();
        return personDetails.getUsername();
    }
}
