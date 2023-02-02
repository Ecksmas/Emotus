package com.example.Group2;

import kong.unirest.*;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    //TODO ersätt med session object
    String endLyrics;



    @GetMapping("/")
    public String viewHomePage(Model model, @RequestParam(required = false, defaultValue = "") String keyword) {

        int resultPerPage = 5;

        try {
            HttpResponse<String> songIdResponse = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/search/?q=" + keyword + "&per_page=" + resultPerPage + "&page=1")
                    .header("X-RapidAPI-Key", "7fdc5ae974msh4665893b2c77710p1523e0jsn4e77becb0728")
                    .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                    .asString();

            JSONObject json = new JSONObject(songIdResponse.getBody());
            JSONArray hits = json.getJSONArray("hits");

            List<Song> songList = new ArrayList<>();

            //Loopar igenom sen lägg till
            for (int i = 0; i < hits.length(); i++) {
                JSONObject result = hits.getJSONObject(i).getJSONObject("result");
                String title = result.getString("title");
                String artist = result.getString("artist_names");
                String songId = result.getString("id");
                Song song = new Song(title, artist, songId);
                songList.add(song);
            }
            model.addAttribute("keyword", keyword);
            model.addAttribute("songList", songList);

            return "test";
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "error";
    }


    @GetMapping("/song")
    public String song(Model model,
                       @RequestParam(required = false, defaultValue = "happy") String title,
                       @RequestParam(required = false) String id) {

        //Grabs song title,artist and lyrics from previous page click
        HttpResponse<String> lyrics = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/song/lyrics/?id=" + id + "&text_format=plain")
                .header("X-RapidAPI-Key", "7fdc5ae974msh4665893b2c77710p1523e0jsn4e77becb0728")
                .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                .asString();

        // Uncomment to see if Genius API is maxed out
        // System.out.println(lyrics.getBody());

        JSONObject json = new JSONObject(lyrics.getBody());
        JSONObject result = json.getJSONObject("lyrics");

        String titleResult = result.getJSONObject("tracking_data").getString("title");
        String artistResult = result.getJSONObject("tracking_data").getString("primary_artist");
        String lyricsResult = result.getJSONObject("lyrics").getJSONObject("body").getString("plain");

        model.addAttribute("titleResult", titleResult);
        model.addAttribute("artistResult", artistResult);

        //Searches for header url
        HttpResponse<String> songIdResponse = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/search/?q=" + titleResult + " " + artistResult + "&per_page=5&page=1")
                .header("X-RapidAPI-Key", "7fdc5ae974msh4665893b2c77710p1523e0jsn4e77becb0728")
                .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                .asString();

        JSONObject jsonForImg = new JSONObject(songIdResponse.getBody());
        JSONArray hitsForImg = jsonForImg.getJSONArray("hits");

        JSONObject resultForImg = hitsForImg.getJSONObject(0).getJSONObject("result");
        String songImg = resultForImg.getString("header_image_url");

        model.addAttribute("songImg", songImg);

        endLyrics = lyricsResult;

        model.addAttribute("lyricsResult", lyricsResult);

        return "Song";
    }

    @GetMapping("/Text")
    public String text(Model model) {

        String text = endLyrics.replace("\n"," ").replace("\"","");

        HttpResponse<String> sentimentResult = Unirest.post("https://sentiment-analysis46.p.rapidapi.com/sentiment")
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
                .header("X-RapidAPI-Host", "sentiment-analysis46.p.rapidapi.com")
                .body("{\r\n    \"text\": \"" + text + "\",\r\n    \"spell_check\": true,\r\n    \"keywords\": true\r\n}")
                .asString();

        JSONObject json = new JSONObject(sentimentResult.getBody());

        String sentimentText = json.getString("sentiment");
        String subjectivityText = json.getString("subjectivity");

        model.addAttribute("sentimentText", sentimentText);
        model.addAttribute("subjectivityText", subjectivityText);

        return "Text";
    }

}
