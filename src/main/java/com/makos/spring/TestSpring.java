package com.makos.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        ClassicMusic music = context.getBean("classicMusic", ClassicMusic.class);
        ClassicMusic music2 = context.getBean("classicMusic", ClassicMusic.class);
        System.out.println(music.getSong());
        System.out.println(music2.getSong());
    }
}
