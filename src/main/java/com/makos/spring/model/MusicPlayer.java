package com.makos.spring.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Getter
@RequiredArgsConstructor

@Component
public class MusicPlayer {

    private final Music music;

    private String name;
    private int volume;


    public void playMusic() {
        System.out.println("Playing: " + music.getSong());;
    }
}