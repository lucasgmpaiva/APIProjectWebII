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
import org.springframework.http.ResponseEntity;

import com.project.movies.entity.MovieEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MovieTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void testPostMovie() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(getJSONMovie().toString(), headers);
		
		MovieEntity movie = testRestTemplate.postForObject("http://localhost:" + port + "/movie", request, MovieEntity.class);
		
		assertEquals("Pulp Fiction", movie.getTitle());
		
	}
	
	@Test
	public void testGetMovieById() {
		
		ResponseEntity<MovieEntity> res = testRestTemplate.getForEntity("http://localhost:" + port + "/movie/1", MovieEntity.class);
		
		MovieEntity movie = res.getBody();
		
		assertEquals(1L, movie.getId());
		
	}
	
	@Test
	public void testGetMovieByDirector() {
		
		ResponseEntity<MovieEntity[]> res = testRestTemplate.getForEntity("http://localhost:" + port + "/movie/search?director=Tarantino", MovieEntity[].class);
		
		MovieEntity[] movies = res.getBody();
		
		for(MovieEntity movie : movies) {
			assertEquals("Quentin Tarantino", movie.getDirector());
		}
		
	}
	
	
	// UTILITÁRIOS
	
	private JSONObject getJSONMovie() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("title", "Pulp Fiction");
		json.put("writer", "Quentin Tarantino");
		json.put("director", "Quentin Tarantino");
		json.put("release_date", "22-09-1994");
		json.put("gender", "Action");
		
		return json;
	}

}
