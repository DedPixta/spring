package com.makos.spring.controller;

import com.makos.spring.model.SecurePerson;
import com.makos.spring.security.SecurePersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String printHello() {
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurePersonDetails personDetails = (SecurePersonDetails) authentication.getPrincipal();
        SecurePerson securePerson = personDetails.getSecurePerson();
        System.out.println(securePerson);
        return "hello";
    }
}