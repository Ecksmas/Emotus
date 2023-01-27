package com.example.Group2;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static java.lang.Integer.parseInt;

@SpringBootTest
class Group2ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void songIdTest() {

		// Connection & response
		HttpResponse<String> songIdResponse = Unirest.get("https://genius-song-lyrics1.p.rapidapi.com/search/?q=happy&per_page=5&page=1")
				.header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
				.header("X-RapidAPI-Host", "genius-song-lyrics1.p.rapidapi.com")
				.asString();

		// Saved Json response in String s
		String s = songIdResponse.getBody();

		// Substring into numerical correctness
		String songId = StringUtils.substringBetween(s, "\\/songs\\/", "\",\"");

		Assertions.assertEquals(parseInt(songId), 154031);
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

		Assertions.assertEquals(sentimentText, "negative");
		Assertions.assertEquals(subjectivityText, "objective");
	}

}
