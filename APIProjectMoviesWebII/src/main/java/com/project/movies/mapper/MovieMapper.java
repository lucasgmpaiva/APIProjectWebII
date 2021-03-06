package com.project.movies.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.movies.entity.MovieEntity;
import com.project.movies.model.Movie;

@Component
public class MovieMapper {
	
	public Movie toModel(MovieEntity movieEntity) {
		
		Movie movie = new Movie();
		
		movie.setId(movieEntity.getId());
		movie.setTitle(movieEntity.getTitle());
		movie.setDirector(movieEntity.getDirector());
		movie.setWriters(movieEntity.getWriter());
		movie.setGender(movieEntity.getGender());
		movie.setRelease_date(movieEntity.getRelease_date());
		
		return movie;
		
	}
	
	public MovieEntity toEntity(Movie movie) {
		
		MovieEntity movieEntity = new MovieEntity();
		
		movieEntity.setId(movie.getId());
		movieEntity.setTitle(movie.getTitle());
		movieEntity.setDirector(movie.getDirector());
		movieEntity.setWriters(movie.getWriter());
		movieEntity.setGender(movie.getGender());
		movieEntity.setRelease_date(movie.getRelease_date());
		
		return movieEntity;
		
		
	}
	
	public Movie optionalToModel (java.util.Optional<MovieEntity> movieEntity) {
		
		Movie movie = new Movie();
		
		movie.setId(movieEntity.get().getId());
		movie.setTitle(movieEntity.get().getTitle());
		movie.setDirector(movieEntity.get().getDirector());
		movie.setWriters(movieEntity.get().getWriter());
		movie.setGender(movieEntity.get().getGender());
		movie.setRelease_date(movieEntity.get().getRelease_date());
		
		return movie;
		
	}
	
	public List<Movie> listToModel(List<MovieEntity> moviesEntity) {
		List<Movie> movies = new ArrayList<Movie>();
		
		for(MovieEntity movieEntity : moviesEntity) {
			movies.add(toModel(movieEntity));
		}
		
		return movies;
		
	}

}
