package com.example.Group2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Group2Application {

	public static void main(String[] args) {
		SpringApplication.run(Group2Application.class, args);


/*		HttpResponse<String> response = Unirest.get("https://spotify23.p.rapidapi.com/search/?q=exuma&type=multi&offset=0&limit=10&numberOfTopResults=5")
				.header("X-RapidAPI-Key", "1c1587b38emsh0e7714653c0660ep10ab75jsnf6e80542980e")
				.header("X-RapidAPI-Host", "spotify23.p.rapidapi.com")
				.asString();

		System.out.println(response.getBody());*/
	}

}
