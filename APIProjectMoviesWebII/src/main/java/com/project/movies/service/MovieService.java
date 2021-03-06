package com.project.movies.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movies.entity.MovieEntity;
import com.project.movies.mapper.MovieMapper;
import com.project.movies.model.Movie;
import com.project.movies.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	MovieMapper mapper;
	
	
	public Movie createMovie(Movie movie) {
	
		MovieEntity movieEntity = mapper.toEntity(movie);
		
		return mapper.toModel(movieRepository.save(movieEntity));
		
	}

	public List<Movie> findAll() {
		return mapper.listToModel(movieRepository.findAll());
	}
	
	public Movie findById(Long id) {
		return mapper.optionalToModel(movieRepository.findById(id));
	}
	
	public List<Movie> searchMovies (String title, String director, String gender) {
		
		List<Movie> movies = findAll();
		List<Movie> foundMovies = new ArrayList<Movie>();
		if(title != null || director != null || gender != null) {
			for (Movie movie : movies) {
				if(title != null && director == null && gender == null) {
					if(movie.getTitle().toLowerCase().contains(title.toLowerCase()))
						foundMovies.add(movie);
				}
				if(director != null && title == null && gender == null) {
					if(movie.getDirector().toLowerCase().contains(director.toLowerCase()))
						foundMovies.add(movie);
				}
				if(director != null && title != null && gender == null) {
					if(movie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& movie.getDirector().toLowerCase().contains(director.toLowerCase()))
						foundMovies.add(movie);
				}
				if(director == null && title != null && gender != null) {
					if(movie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& movie.getGender().toLowerCase().contains(gender.toLowerCase()))
						foundMovies.add(movie);
				}
				if(director != null && title == null && gender != null) {
					if(movie.getDirector().toLowerCase().contains(director.toLowerCase())
							&& movie.getGender().toLowerCase().contains(gender.toLowerCase()))
						foundMovies.add(movie);
				}
				if(gender != null && director == null && title == null) {
					if(movie.getGender().toLowerCase().contains(gender.toLowerCase()))
						foundMovies.add(movie);
				}
				if(director != null && title != null && gender != null) {
					if(movie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& movie.getGender().toLowerCase().contains(gender.toLowerCase())
							&& movie.getDirector().toLowerCase().contains(director.toLowerCase()))
						foundMovies.add(movie);
				}
			}
		}
		return foundMovies;
		
	}
	
}
