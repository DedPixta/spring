package com.makos.spring.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Getter

@Component
public class MusicPlayer {


    private final Music music;

    public MusicPlayer(@Qualifier(value = "classicMusic") Music music) {
        this.music = music;
    }

    public String playMusic() {
        System.out.println("Playing: " + music.getSong());
        return music.getSong();
    }
}