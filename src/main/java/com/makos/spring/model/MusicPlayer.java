package com.makos.spring.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@RequiredArgsConstructor

@Component
public class MusicPlayer {

    private final List<Music> musicList;

    private String name;
    private int volume;


    public void playMusic() {
       musicList.forEach(music -> System.out.println("Playing: " + music.getSong()));
    }
}