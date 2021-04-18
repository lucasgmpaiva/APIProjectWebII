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
	
	@Test void testGetSerieById() {
		
		SerieEntity serie = testRestTemplate.getForObject("http://localhost:" + port + "/serie/1", SerieEntity.class);
		
		assertEquals(1L, serie.getId());
		
	}
	
	@Test void testGetSerieByTitle() {
		
		ResponseEntity<SerieEntity[]> res = testRestTemplate.getForEntity("http://localhost:" + port + "/serie/search?title=Avenida Brasil", SerieEntity[].class);
		
		SerieEntity[] series = res.getBody();
		
		for(SerieEntity serie : series) {
			assertEquals("Avenida Brasil", serie.getTitle());
		}
		
	}
	
	@Test void testGetAll() {
		ResponseEntity<SerieEntity[]> res = testRestTemplate.getForEntity("http://localhost:" + port + "/serie/all", SerieEntity[].class);
		
		SerieEntity[] series = res.getBody();
		
		assertEquals(3, series.length);
	}
	
	@Test void testDeleteSerie() {
		
		testRestTemplate.delete("http://localhost:" + port + "/serie/3");
		
		Optional<SerieEntity> serie = serieRepository.findById(3L);
		
		assertTrue(serie.isEmpty());
	}
	
	@Test void testUpdateSerie() throws JSONException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<String>(getJSONSerieUpdate().toString(), headers);
		
		testRestTemplate.put("http://localhost:" + port + "/serie/3", request, SerieEntity.class);
		
		Optional<SerieEntity> serie = serieRepository.findById(3L);
		
		assertEquals("Game of Thrones", serie.get().getTitle());
		
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
	
	private JSONObject getJSONSerieUpdate() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("title", "Game of Thrones");
		json.put("broadcaster", "HBO");
		json.put("release_date", "2011");
		json.put("gender", "Epic");
		
		return json;
	}

}
