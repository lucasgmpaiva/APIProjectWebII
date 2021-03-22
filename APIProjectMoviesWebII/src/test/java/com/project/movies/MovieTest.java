package com.project.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.project.movies.entity.MovieEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MovieTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
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

}
