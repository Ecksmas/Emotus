package com.example.Group2;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private SongService service;

    @GetMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        model.addAttribute("keyword", keyword);

        return "HomePage";
    }



    @GetMapping("/song")
    public String song(Model model, @RequestParam(required = false, defaultValue = "happy") String key) {
        HttpResponse<String> songIdResponse = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/search/?q=" + key + "&per_page=1&page=1")
                .header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
                .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                .asString();

        String s = songIdResponse.getBody();
        //hämtar song ID från json data använd inte siffror.
        String songId = s.substring(122, 128);

        // fungerar bättre snedstreck, måste uppdatera till något bättre som fungerar för alla lyrics.
        songId= songId.replace("/", "");

        HttpResponse<String> lyrics = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/song/lyrics/?id=" + songId + "&text_format=plain")
                .header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
                .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                .asString();


        String lyricsResult = lyrics.getBody();

        //String songIdResult = lyricsResult.substring(lyricsResult.indexOf("plain:"), lyricsResult.indexOf("path:"));

        String finalLyrics = StringUtils.substringBetween(lyricsResult, "plain\":\"", "\"}},\"");

        // vill ta bort \n och []


        finalLyrics = finalLyrics.replace("\\n", " ");


        model.addAttribute("ID", finalLyrics);

        // Ludwigs sparade rad <3 System.out.println(finalLyrics);

        return "Song";

    }
// komma åt lyric mellan plain:" och "path


//    @GetMapping("/search")
//    public String search(Model model) {
//        HttpResponse<String> response = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/song/lyrics/?id=2396871&text_format=plain")
//                .header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
//                .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
//                .asString();
//        model.addAttribute("ID", response.getBody());
//
//        //TODO Parse json string
//        //TODO Song id must be song
//
//        return "ResultPage";
//    }

}
