package com.project.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.project.movies.entity.MovieEntity;
import com.project.movies.entity.SerieEntity;
import com.project.movies.repository.SerieRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SerieTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private SerieRepository serieRepository;
	
	@Test void testPostSerie() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(getJSONSerie().toString(), headers);
		
		SerieEntity serie = testRestTemplate.postForObject("http://localhost:" + port + "/serie", request, SerieEntity.class);
		
		assertEquals("FRIENDS", serie.getTitle());
		
	}
	
	
	// Utilitários
	
	private JSONObject getJSONSerie() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("title", "FRIENDS");
		json.put("broadcaster", "NBC");
		json.put("release_date", "22-09-1994");
		json.put("gender", "Comedy");
		
		return json;
	}

}
