package com.project.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

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
import com.project.movies.repository.MovieRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MovieTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Test void testPostMovie() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(getJSONMovie().toString(), headers);
		
		MovieEntity movie = testRestTemplate.postForObject("http://localhost:" + port + "/movie", request, MovieEntity.class);
		
		assertEquals("Pulp Fiction", movie.getTitle());
		
	}
	
	@Test void testGetMovieById() {
		
		MovieEntity movie = testRestTemplate.getForObject("http://localhost:" + port + "/movie/1", MovieEntity.class);
		
		assertEquals(1L, movie.getId());
		
	}
	
	@Test void testGetMovieByDirector() {
		
		ResponseEntity<MovieEntity[]> res = testRestTemplate.getForEntity("http://localhost:" + port + "/movie/search?director=Me", MovieEntity[].class);
		
		MovieEntity[] movies = res.getBody();
		
		for(MovieEntity movie : movies) {
			assertEquals("Me", movie.getDirector());
		}
		
	}
	
	@Test void testGetAll() {
		ResponseEntity<MovieEntity[]> res = testRestTemplate.getForEntity("http://localhost:" + port + "/movie/all", MovieEntity[].class);
		
		MovieEntity[] movies = res.getBody();
		
		assertEquals(3, movies.length);
	}
	
	
	
	@Test void testDeleteMovie() {
		
		testRestTemplate.delete("http://localhost:" + port + "/movie/2");
		
		Optional<MovieEntity> movie = movieRepository.findById(2L);
		
		assertTrue(movie.isEmpty());
	}
	
	@Test void testUpdateMovie() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(getJSONMovieUpdate().toString(), headers);
		
		testRestTemplate.put("http://localhost:" + port + "/movie/3", request, MovieEntity.class);
		
		Optional<MovieEntity> movie = movieRepository.findById(3L);
		
		assertEquals("Jurassic Park", movie.get().getTitle());
	}
	
	
	// UTILIT??RIOS
	
	private JSONObject getJSONMovie() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("title", "Pulp Fiction");
		json.put("writer", "Quentin Tarantino");
		json.put("director", "Quentin Tarantino");
		json.put("release_date", "21-05-1994");
		json.put("gender", "Action");
		
		return json;
	}
	
	private JSONObject getJSONMovieUpdate() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("title", "Jurassic Park");
		json.put("writer", "Michael Crichton");
		json.put("director", "Steven Spielberg");
		json.put("release_date", "13-06-1993");
		json.put("gender", "Sci-Fi");
		
		return json;
	}


}
