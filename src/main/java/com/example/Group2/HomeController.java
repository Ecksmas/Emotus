package com.example.Group2;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
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
        List<Song> listSongs = service.listAll(keyword);
        model.addAttribute("listSongs", listSongs);
        model.addAttribute("keyword", keyword);

        return "HomePage";
    }
    //

    @GetMapping("/song")
            public String song(Model model){
    HttpResponse<String> songIdResponse = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/search/?q=happy&per_page=1&page=1")
            .header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
            .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
            .asString();
    model.addAttribute("ID", songIdResponse.getBody());

    String songId = String.valueOf(songIdResponse);
        System.out.println(songId);

 /*       HttpResponse<String> lyrics = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/song/lyrics/?id=" + songId + "&text_format=plain")
                .header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
                .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                .asString();
        model.addAttribute("ID", lyrics.getBody());*/
    return "Song";
    }


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
