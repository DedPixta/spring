package com.makos.spring.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class ClassicMusic implements Music{

    private ClassicMusic() {}

    public void init() {
        System.out.println("Initializing");
    }

    public void destroy() {
        System.out.println("Destroy");
    }

    @Override
    public String getSong() {
        return "Classical song";
    }

    public static ClassicMusic getNewMusic(){
        return new ClassicMusic();
    }
}
