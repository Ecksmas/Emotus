package com.example.Group2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository repository;
    public List<Song> listAll(String keyword) {
        if (keyword != null) {
            return repository.search(keyword);
        }
        return repository.findAll();
    }

}
