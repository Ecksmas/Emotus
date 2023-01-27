package com.example.Group2;

public class Song {
    String artist;
    String title;
    String songId;

    public Song(String artist, String title, String songId) {
        this.artist = artist;
        this.title = title;
        this.songId = songId;
    }

    public Song() {
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    @Override
    public String toString() {
        return "Song{" +
                "Artist:'" + artist + '\'' +
                ", Title:'" + title + '\'' +
                ", hitSongId='" + songId + '\'' +
                '}';
    }
}
