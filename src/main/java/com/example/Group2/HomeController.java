package com.example.Group2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private SongRepository repository;


    @GetMapping("/")
    public String Start() {

        return "HomePage";
    }

    @PostMapping("/")
    public String Search() {

        return "HomePage";
    }


}
