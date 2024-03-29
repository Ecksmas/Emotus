package com.example.Group2;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class Group2ApplicationTests {

	@InjectMocks
	private HomeController homeController;

	@Mock
	private Model model;

	@Test
	void contextLoads() {
	}

	@Test
	public void viewHomePageTest_success() {
		when(model.addAttribute(anyString(), anyString())).thenReturn(model);

		String result = homeController.viewHomePage(model, "keyword");

		assertEquals("test", result);
	}

	@Test
	public void songIdTest() {

		// Connection & response
		HttpResponse<String> songIdResponse = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/search/?q=happy&per_page=5&page=1")
				.header("X-RapidAPI-Key", "7228fd083cmshf3813a6be37c56fp122391jsn974535b54c88")
				.header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
				.asString();

		// Saved Json response in String s
		String s = songIdResponse.getBody();

		// Substring into numerical correctness
		String songId = StringUtils.substringBetween(s, "\\/songs\\/", "\",\"");

		assertEquals(parseInt(songId), 154031);
	}

	@Test
	public void sentimentAnalysisTest() {

		// Chosen word to test
		String lyricalInput = "murder";

		// Connection & response
		HttpResponse<String> sentimentResult = Unirest.post("https://sentiment-analysis46.p.rapidapi.com/sentiment")
				.header("content-type", "application/json")
				.header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
				.header("X-RapidAPI-Host", "sentiment-analysis46.p.rapidapi.com")
				.body("{\r\n    \"text\": \"" + lyricalInput + "\",\r\n    \"spell_check\": true,\r\n    \"keywords\": true\r\n}")
				.asString();

		String sentimentResultBody = sentimentResult.getBody();

		String sentimentText = StringUtils.substringBetween(sentimentResultBody, "\"sentiment\": \"", "\", \"");
		String subjectivityText = StringUtils.substringBetween(sentimentResultBody, "subjectivity\": \"", "\", \"");

		assertEquals(sentimentText, "negative");
		assertEquals(subjectivityText, "objective");
	}

	@Test
	public void	songClassAddTest() {
		HttpResponse<String> songIdResponse = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/search/?q=" + "happy" + "&per_page=5&page=1")
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

		// Picking them in the wrong order.
		assertEquals(songList.get(0).getTitle(), "Pharrell Williams");
		assertEquals(songList.get(0).getArtist(), "Happy");
	}

}
