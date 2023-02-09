package com.makos.spring.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam("name") String name,
                            @RequestParam(value = "surename", required = false) String surename){

        System.out.println("hello, " + name + " " + surename);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(HttpServletRequest request){
        String name = request.getParameter("name");
        String surename = request.getParameter("surename");


        System.out.println("buy, " + name + " " + surename);
        return "first/goodbye";
    }
}
