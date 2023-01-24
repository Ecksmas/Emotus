package com.example.Group2;

import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

}
