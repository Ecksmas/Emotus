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

    String endLyrics;

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
        System.out.println(s);
        //Fetch song ID from JSON string and substring into components
        String songId = StringUtils.substringBetween(s, "\\/songs\\/", "\",\"");
        String artistName = StringUtils.substringBetween(s, "\"artist_names\":\"", "\",\"");
        String songTitle = StringUtils.substringBetween(s, "\"full_title\":\"", "by\\u00a0");
        String songImg = StringUtils.substringBetween(s, "\"header_image_url\":\"", "\",\"");
        songImg = songImg.replace("\\","");

        model.addAttribute("artistName", artistName);
        model.addAttribute("songTitle", songTitle);
        model.addAttribute("songImg", songImg);


        HttpResponse<String> lyrics = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/song/lyrics/?id=" + songId + "&text_format=plain")
                .header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
                .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                .asString();


        String lyricsResult = lyrics.getBody();

        //String songIdResult = lyricsResult.substring(lyricsResult.indexOf("plain:"), lyricsResult.indexOf("path:"));

        String finalLyrics = StringUtils.substringBetween(lyricsResult, "plain\":\"", "\"}},\"");

        finalLyrics = finalLyrics.replace("\\n", " ");
        finalLyrics = finalLyrics.replace("\\u2019", "'");
        finalLyrics = finalLyrics.replace("\\u2014", "-");

        endLyrics = finalLyrics;

        model.addAttribute("ID", finalLyrics);

        // Ludwigs sparade rad <3 System.out.println(finalLyrics);

        return "Song";

    }

    @GetMapping("/Text")
    public String text(Model model) {
        HttpResponse<String> sentimentResult = Unirest.post("https://sentiment-analysis46.p.rapidapi.com/sentiment")
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
                .header("X-RapidAPI-Host", "sentiment-analysis46.p.rapidapi.com")
                .body("{\r\n    \"text\": \"" + endLyrics + "\",\r\n    \"spell_check\": true,\r\n    \"keywords\": true\r\n}")
                .asString();

        System.out.println(sentimentResult.getBody());
        String sentimentResultBody = sentimentResult.getBody();

        String sentimentText = StringUtils.substringBetween(sentimentResultBody, "\"sentiment\": \"", "\", \"");
        String subjectivityText = StringUtils.substringBetween(sentimentResultBody, "subjectivity\": \"", "\", \"");

        model.addAttribute("sentimentText", sentimentText);
        model.addAttribute("subjectivityText", subjectivityText);

        return "Text";
    }

}
