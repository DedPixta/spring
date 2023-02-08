package com.makos.spring;

import com.makos.spring.model.Computer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        Computer computer = context.getBean(Computer.class);

        System.out.println(computer);

        context.close();
    }
}
