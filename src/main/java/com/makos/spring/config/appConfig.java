package com.makos.spring.config;

import com.makos.spring.model.JazzMusic;
import com.makos.spring.model.Music;
import com.makos.spring.model.MusicPlayer;
import com.makos.spring.model.RockMusic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class appConfig {


    @Bean
    public RockMusic rockMusic(){
        return new RockMusic();
    }

    @Bean
    public JazzMusic jazzMusic(){
        return new JazzMusic();
    }

    @Bean
    public MusicPlayer musicPlayer() {
        return new MusicPlayer(Arrays.asList(rockMusic(), jazzMusic()));
    }

}
