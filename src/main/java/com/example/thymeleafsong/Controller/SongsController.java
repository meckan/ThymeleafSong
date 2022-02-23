package com.example.thymeleafsong.Controller;

import com.example.thymeleafsong.DBHandler.CustomerService;
import com.example.thymeleafsong.DBHandler.SongsRepository;
import com.example.thymeleafsong.DBHandler.SongsService;
import com.example.thymeleafsong.DTO.SongDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SongsController {

    private final SongsService service;

    public SongsController(SongsService service){
        this.service = service;
    }

    @GetMapping("/home")
    public String getRandomMusicData(Model model){
//        SongsService service = new SongsService();
        String randomSongs = "";
        for(String song : service.getRandomSongs(5)){
            randomSongs += song + ". ";
        }

        String randomArtists = "";
        for(String artist : service.getRandomArtists(5)){
            randomArtists += artist + ". ";
        }

        String randomGenres = "";
        for(String genres : service.getRandomGenres(5)){
            randomGenres += genres + ". ";
        }

        model.addAttribute("randomSongs",randomSongs);
        model.addAttribute("randomArtists",randomArtists);
        model.addAttribute("randomGenres",randomGenres);
        return "home";
    }

    String song = "";
    @PostMapping("result")
    public String searchSong(@RequestParam("song") String song, Model model){

        this.song = song;
        model.addAttribute("song",song);
        return "result";
    }

    @GetMapping("/result")
    public String resultPage(Model model) {
        model.addAttribute("song", song);
        return "result";
    }
}
