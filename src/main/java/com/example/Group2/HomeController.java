package com.example.Group2;

import com.fasterxml.jackson.databind.SerializationFeature;
import kong.unirest.*;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    String endLyrics;

    @GetMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        model.addAttribute("keyword", keyword);

        int resultPerPage = 1;

        HttpResponse<String> songIdResponse = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/search/?q=" + keyword + "&per_page=" + resultPerPage + "&page=1")
                .header("X-RapidAPI-Key", "7228fd083cmshf3813a6be37c56fp122391jsn974535b54c88")
                .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                .asString();

        model.addAttribute("Result", songIdResponse.getBody());

        return "Index";
    }

    /*
    @GetMapping("/")
    public String viewHomePage(Model model, @RequestParam(required = false, defaultValue = "happy") String keyword) {


        int resultPerPage = 5;

        try {
            HttpResponse<String> songIdResponse = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/search/?q=" + keyword + "&per_page=" + resultPerPage + "&page=1")
                    .header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
                    .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                    .asString();

            model.addAttribute("keyword", keyword);

            {"hits":[{"highlights":[],"index":"song","type":"song","result":{"_type":"song","annotation_count":6,"api_path":"/songs/154031","artist_names":"Pharrell Williams","full_title":"Happy by Pharrell Williams","header_image_thumbnail_url":"https://images.genius.com/0712acd53d97c0961659f8228847da34.300x300x1.jpg","header_image_url":"https://images.genius.com/0712acd53d97c0961659f8228847da34.1000x1000x1.jpg","id":154031,"instrumental":false,"language":"en","lyrics_owner_id":152635,"lyrics_state":"complete","lyrics_updated_at":1661620818,"path":"/Pharrell-williams-happy-lyrics","pyongs_count":883,"relationships_index_url":"https://genius.com/Pharrell-williams-happy-sample","release_date_components":{"year":2013,"month":11,"day":21},"release_date_for_display":"November 21, 2013","release_date_with_abbreviated_month_for_display":"Nov. 21, 2013","song_art_image_thumbnail_url":"https://images.genius.com/08c9cccbe508d70ae05be9c0e7d05358.300x300x1.jpg","song_art_image_url":"https://images.genius.com/08c9cccbe508d70ae05be9c0e7d05358.1000x1000x1.jpg","stats":{"unreviewed_annotations":0,"concurrents":3,"hot":false,"pageviews":2103607},"title":"Happy","title_with_featured":"Happy","updated_by_human_at":1666350094,"url":"https://genius.com/Pharrell-williams-happy-lyrics","featured_artists":[],"primary_artist":{"_type":"artist","api_path":"/artists/110","header_image_url":"https://images.genius.com/6bb3ce5bd62c7acfc3a66798c4bbeb26.640x427x1.jpg","id":110,"image_url":"https://images.genius.com/aa964e94a21e7b893801ed3a2bd389b8.1000x1000x1.jpg","index_character":"p","is_meme_verified":true,"is_verified":true,"name":"Pharrell Williams","slug":"Pharrell-williams","url":"https://genius.com/artists/Pharrell-williams","iq":692}}},
                {"highlights":[],"index":"song","type":"song","result":{"_type":"song","annotation_count":2,"api_path":"/songs/5708126","artist_names":"Dixie","full_title":"Be Happy by Dixie","header_image_thumbnail_url":"https://images.genius.com/1d5966752f4de38206da959b13d11809.300x300x1.png","header_image_url":"https://images.genius.com/1d5966752f4de38206da959b13d11809.905x905x1.png","id":5708126,"instrumental":false,"language":"en","lyrics_owner_id":3973518,"lyrics_state":"complete","lyrics_updated_at":1655062166,"path":"/Dixie-be-happy-lyrics","pyongs_count":48,"relationships_index_url":"https://genius.com/Dixie-be-happy-sample","release_date_components":{"year":2020,"month":6,"day":26},"release_date_for_display":"June 26, 2020","release_date_with_abbreviated_month_for_display":"Jun. 26, 2020","song_art_image_thumbnail_url":"https://images.genius.com/54414176b76ec7b104b323194f813bf3.300x300x1.jpg","song_art_image_url":"https://images.genius.com/54414176b76ec7b104b323194f813bf3.1000x1000x1.jpg","stats":{"unreviewed_annotations":0,"hot":false,"pageviews":648556},"title":"Be Happy","title_with_featured":"Be Happy","updated_by_human_at":1667024257,"url":"https://genius.com/Dixie-be-happy-lyrics","featured_artists":[],"primary_artist":{"_type":"artist","api_path":"/artists/2297506","header_image_url":"https://images.genius.com/7eeca3eec75100fa4b14bd9018a7c6d3.1000x333x1.jpg","id":2297506,"image_url":"https://images.genius.com/3156582bcedc575e104ec8175e11d87d.1000x1000x1.jpg","index_character":"d","is_meme_verified":false,"is_verified":false,"name":"Dixie","slug":"Dixie","url":"https://genius.com/artists/Dixie"}}},
                {"highlights":[],"index":"song","type":"song","result":{"_type":"song","annotation_count":3,"api_path":"/songs/3692157","artist_names":"Zedd & Elley Duhé","full_title":"Happy Now by Zedd & Elley Duhé","header_image_thumbnail_url":"https://images.genius.com/cb2b2a5798f8b550ee6ce87f56405c2a.300x300x1.png","header_image_url":"https://images.genius.com/cb2b2a5798f8b550ee6ce87f56405c2a.1000x1000x1.png","id":3692157,"instrumental":false,"language":"en","lyrics_owner_id":4509691,"lyrics_state":"complete","lyrics_updated_at":1664844575,"path":"/Zedd-and-elley-duhe-happy-now-lyrics","pyongs_count":21,"relationships_index_url":"https://genius.com/Zedd-and-elley-duhe-happy-now-sample","release_date_components":{"year":2018,"month":7,"day":18},"release_date_for_display":"July 18, 2018","release_date_with_abbreviated_month_for_display":"Jul. 18, 2018","song_art_image_thumbnail_url":"https://images.genius.com/cb2b2a5798f8b550ee6ce87f56405c2a.300x300x1.png","song_art_image_url":"https://images.genius.com/cb2b2a5798f8b550ee6ce87f56405c2a.1000x1000x1.png","stats":{"unreviewed_annotations":1,"hot":false,"pageviews":451072},"title":"Happy Now","title_with_featured":"Happy Now","updated_by_human_at":1664844575,"url":"https://genius.com/Zedd-and-elley-duhe-happy-now-lyrics","featured_artists":[],"primary_artist":{"_type":"artist","api_path":"/artists/1525922","header_image_url":"https://images.genius.com/7d6d266bb468df45a0a6a3a66a907a8d.600x600x1.jpg","id":1525922,"image_url":"https://images.genius.com/69afd2e8911ef2b16f1ab32946abaf2b.680x680x1.png","index_character":"z","is_meme_verified":false,"is_verified":false,"name":"Zedd & Elley Duhé","slug":"Zedd-and-elley-duhe","url":"https://genius.com/artists/Zedd-and-elley-duhe"}}},
                {"highlights":[],"index":"song","type":"song","result":{"_type":"song","annotation_count":5,"api_path":"/songs/3807763","artist_names":"Drake","full_title":"Ratchet Happy Birthday by Drake","header_image_thumbnail_url":"https://images.genius.com/5c677b7bfc2f696abf2e2d928301bb44.300x300x1.jpg","header_image_url":"https://images.genius.com/5c677b7bfc2f696abf2e2d928301bb44.1000x1000x1.jpg","id":3807763,"instrumental":false,"language":"en","lyrics_owner_id":104344,"lyrics_state":"complete","lyrics_updated_at":1664245428,"path":"/Drake-ratchet-happy-birthday-lyrics","pyongs_count":39,"relationships_index_url":"https://genius.com/Drake-ratchet-happy-birthday-sample","release_date_components":{"year":2018,"month":6,"day":29},"release_date_for_display":"June 29, 2018","release_date_with_abbreviated_month_for_display":"Jun. 29, 2018","song_art_image_thumbnail_url":"https://images.genius.com/5c677b7bfc2f696abf2e2d928301bb44.300x300x1.jpg","song_art_image_url":"https://images.genius.com/5c677b7bfc2f696abf2e2d928301bb44.1000x1000x1.jpg","stats":{"unreviewed_annotations":1,"hot":false,"pageviews":380207},"title":"Ratchet Happy Birthday","title_with_featured":"Ratchet Happy Birthday","updated_by_human_at":1670399329,"url":"https://genius.com/Drake-ratchet-happy-birthday-lyrics","featured_artists":[],"primary_artist":{"_type":"artist","api_path":"/artists/130","header_image_url":"https://images.genius.com/660da2a2d6c98034963ce733c8d9d816.308x164x1.jpg","id":130,"image_url":"https://images.genius.com/26104e61a238b70abfbad57be3de4359.1000x1000x1.jpg","index_character":"d","is_meme_verified":false,"is_verified":false,"name":"Drake","slug":"Drake","url":"https://genius.com/artists/Drake"}}},
                {"highlights":[],"index":"song","type":"song","result":{"_type":"song","annotation_count":2,"api_path":"/songs/3094718","artist_names":"​​Lil Happy Lil Sad","full_title":"​let me die by ​​Lil Happy Lil Sad","header_image_thumbnail_url":"https://images.genius.com/5bf6dc055f8fef31113d4871d49d2898.300x300x1.jpg","header_image_url":"https://images.genius.com/5bf6dc055f8fef31113d4871d49d2898.500x500x1.jpg","id":3094718,"instrumental":false,"language":"en","lyrics_owner_id":4676279,"lyrics_state":"complete","lyrics_updated_at":1671211951,"path":"/Lil-happy-lil-sad-let-me-die-lyrics","pyongs_count":57,"relationships_index_url":"https://genius.com/Lil-happy-lil-sad-let-me-die-sample","release_date_components":{"year":2016,"month":12,"day":20},"release_date_for_display":"December 20, 2016","release_date_with_abbreviated_month_for_display":"Dec. 20, 2016","song_art_image_thumbnail_url":"https://images.genius.com/511889339f13ec7c97908e7d4a0274f3.300x300x1.jpg","song_art_image_url":"https://images.genius.com/511889339f13ec7c97908e7d4a0274f3.1000x1000x1.jpg","stats":{"unreviewed_annotations":2,"hot":false,"pageviews":416466},"title":"​let me die","title_with_featured":"​let me die","updated_by_human_at":1671211951,"url":"https://genius.com/Lil-happy-lil-sad-let-me-die-lyrics","featured_artists":[],"primary_artist":{"_type":"artist","api_path":"/artists/1130542","header_image_url":"https://images.genius.com/320c1fefcdfacc3f4b28f124ba99be35.1000x210x1.jpg","id":1130542,"image_url":"https://images.genius.com/77e28037bdc310b55d94f3b9e4f44368.881x881x1.jpg","index_character":"l","is_meme_verified":false,"is_verified":false,"name":"​​Lil Happy Lil Sad","slug":"Lil-happy-lil-sad","url":"https://genius.com/artists/Lil-happy-lil-sad"}}}]}



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
            model.addAttribute("songList", songList);

            return "test";
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "error";
    }
    */

    @GetMapping("/song")
    public String song(Model model, @RequestParam(required = false, defaultValue = "happy") String key) {
        HttpResponse<String> songIdResponse = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/search/?q=" + key + "&per_page=5&page=1")
                .header("X-RapidAPI-Key", "7228fd083cmshf3813a6be37c56fp122391jsn974535b54c88")
                .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                .asString();


        //Gör om till JsonObject!!
        String s = songIdResponse.getBody();

        //Fetch song ID from JSON string and substring into components
        String songId = StringUtils.substringBetween(s, "\\/songs\\/", "\",\"");
        String artistName = StringUtils.substringBetween(s, "\"artist_names\":\"", "\",\"");
        String songTitle = StringUtils.substringBetween(s, "\"full_title\":\"", "by\\u00a0");
        String songImg = StringUtils.substringBetween(s, "\"header_image_url\":\"", "\",\"");
        songImg = songImg.replace("\\", "");

        model.addAttribute("artistName", artistName);
        model.addAttribute("songTitle", songTitle);
        model.addAttribute("songImg", songImg);

        HttpResponse<String> lyrics = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/song/lyrics/?id=" + songId + "&text_format=plain")
                .header("X-RapidAPI-Key", "7228fd083cmshf3813a6be37c56fp122391jsn974535b54c88")
                .header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
                .asString();

        String lyricsResult = lyrics.getBody();

        String finalLyrics = StringUtils.substringBetween(lyricsResult, "plain\":\"", "\"}},\"");

        finalLyrics = finalLyrics.replace("\\n", " ");
        finalLyrics = finalLyrics.replace("\\u2019", "'");
        finalLyrics = finalLyrics.replace("\\u2014", "-");

        endLyrics = finalLyrics;

        model.addAttribute("ID", finalLyrics);

        return "Song";
    }

    @GetMapping("/Text")
    public String text(Model model) {
        HttpResponse<String> sentimentResult = Unirest.post("https://sentiment-analysis46.p.rapidapi.com/sentiment")
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", "7228fd083cmshf3813a6be37c56fp122391jsn974535b54c88")
                .header("X-RapidAPI-Host", "sentiment-analysis46.p.rapidapi.com")
                .body("{\r\n    \"text\": \"" + endLyrics + "\",\r\n    \"spell_check\": true,\r\n    \"keywords\": true\r\n}")
                .asString();

        String sentimentResultBody = sentimentResult.getBody();

        String sentimentText = StringUtils.substringBetween(sentimentResultBody, "\"sentiment\": \"", "\", \"");
        String subjectivityText = StringUtils.substringBetween(sentimentResultBody, "subjectivity\": \"", "\", \"");

        model.addAttribute("sentimentText", sentimentText);
        model.addAttribute("subjectivityText", subjectivityText);

        return "Text";
    }

}
