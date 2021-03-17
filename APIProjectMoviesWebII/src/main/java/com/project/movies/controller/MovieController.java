package com.project.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.movies.model.Movie;
import com.project.movies.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	@PostMapping
	public Movie createMovie(@RequestBody Movie movie ) {
		return service.createMovie(movie);
	}
	
	@GetMapping("/all")
	public List<Movie> getAllMovies() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public Movie getById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping("/search")
	public List<Movie> getByTitle(@RequestParam(value = "title", required = false) String title,
									@RequestParam(value = "director", required = false) String director,
									@RequestParam(value = "gender", required = false) String gender) {
		return service.searchMovies(title, director, gender);
	}
	
	@DeleteMapping("/{id}")
	public String deleteMovie(@PathVariable("id") Long id) {
		return service.deleteMovieById(id);
	}
	
	@PutMapping("/{id}")
	public Movie editMovie(@PathVariable("id") Long id,
							@RequestBody Movie movie) {
		movie.setId(id);
		return service.editMovie(movie);		
	}
	
	
}
