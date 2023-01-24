package com.example.Group2;

import jakarta.persistence.*;

@Entity
@Table(name = "SONG")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "ARTIST")
    private String artist;
    @Column(name = "GENRE")
    private String genre;
    @Column(name = "EMOTION")
    private String emotion;

    public Song(Long id, String title, String artist, String genre, String emotion) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.emotion = emotion;
    }

    public Song() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
}
