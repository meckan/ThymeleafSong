package com.example.thymeleafsong.Controller;

import com.example.thymeleafsong.BuissnesModels.Song;
import com.example.thymeleafsong.DBHandler.ArtistService;
import com.example.thymeleafsong.DBHandler.GenreService;
import com.example.thymeleafsong.DBHandler.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class HomePageController {
    private final GenreService genreService;
    private final SongsService songsService;
    private final ArtistService artistService;

    @Autowired
    public HomePageController(ArtistService artistService,
                              SongsService songsService,
                              GenreService genreService)
    {
        this.artistService = artistService;
        this.songsService = songsService;
        this.genreService = genreService;
    }

    @GetMapping("/home")
    public String getRandomMusicData(Model model){
        String randomSongs = "";
        for(String song : songsService.getRandomSongs(5)){
            randomSongs += song + ". ";
        }

        model.addAttribute("randomSongs",randomSongs);

        String randomGenres = "";
        for(String genres : genreService.getRandomGenres(5)){
            randomGenres += genres + ". ";
        }

        model.addAttribute("randomGenres",randomGenres);

        String randomArtists = "";
        for(String artist : artistService.getRandomArtists(5)){
            randomArtists += artist + ". ";
        }

        model.addAttribute("randomArtists",randomArtists);

        return "home";
    }


    @GetMapping("/result")
    public String getSongsByTitle(@RequestParam("sourceText") String sourceText, Model model) {
        model.addAttribute("songs", songsService.getSongByTitle(sourceText));
        return "result";
    }

}
