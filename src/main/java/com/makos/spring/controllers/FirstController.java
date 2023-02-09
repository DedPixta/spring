package com.makos.spring.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surename", required = false) String surename,
                            Model model) {
        model.addAttribute("message", "Hello, " + name + " " + surename);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surename = request.getParameter("surename");


        System.out.println("buy, " + name + " " + surename);
        return "first/goodbye";
    }

    @GetMapping("/calc")
    public String calculator(@RequestParam(value = "a", required = false) Integer a,
                             @RequestParam(value = "b", required = false) Integer b,
                             @RequestParam(value = "action", required = false) String action,
                             Model model) {
        double answer = 0;

        if ("multiplication".equals(action)) {
            answer = a * b;
        } else if ("addition".equals(action)) {
            answer = a + b;
        } else if ("subtraction".equals(action)) {
            answer = a - b;
        } else if ("division".equals(action)) {
            answer = (double) a / b;
        }

        model.addAttribute("answer", answer);

        return "first/calculator";
    }
}
