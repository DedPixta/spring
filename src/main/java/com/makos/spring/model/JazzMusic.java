package com.makos.spring.model;

import org.springframework.stereotype.Component;

@Component
public class JazzMusic implements Music{
    @Override
    public String getSong() {
        return "Jazz song";
    }
}
