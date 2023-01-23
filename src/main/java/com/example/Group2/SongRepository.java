package com.example.Group2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("SELECT s FROM Song s WHERE CONCAT(s.title, ' ', s.artist, ' ', s.genrer, ' ', s.emotion) LIKE %?1%")
    public List<Song> search(String keyword);

}
