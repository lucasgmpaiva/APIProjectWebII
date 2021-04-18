package com.project.movies.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.project.movies.entity.MovieEntity;
import com.project.movies.model.Movie;

@Component
public class MovieMapper {
	
	Logger logger = LoggerFactory.getLogger(MovieMapper.class);
	
	public Movie toModel(MovieEntity movieEntity) {
		
		logger.trace("Executing toModel.");
		
		logger.info("Convertendo filme em modelo para JSON.");		
		Movie movie = new Movie();
		
		movie.setId(movieEntity.getId());
		movie.setTitle(movieEntity.getTitle());
		movie.setDirector(movieEntity.getDirector());
		movie.setWriters(movieEntity.getWriter());
		movie.setGender(movieEntity.getGender());
		movie.setRelease_date(movieEntity.getRelease_date());
		
		logger.info("Filme convertido em modelo para JSON.");
		return movie;
		
	}
	
	public MovieEntity toEntity(Movie movie) {
		
		logger.trace("Executing toEntity.");
		
		logger.info("Convertendo filme em entidade do banco de dados.");
		
		MovieEntity movieEntity = new MovieEntity();

		movieEntity.setId(movie.getId());
		movieEntity.setTitle(movie.getTitle());
		movieEntity.setDirector(movie.getDirector());
		movieEntity.setWriters(movie.getWriter());
		movieEntity.setGender(movie.getGender());
		movieEntity.setRelease_date(movie.getRelease_date());
		
		logger.info("Filme convertido em entidade de banco.");
		return movieEntity;
		
		
	}
	
	public Movie optionalToModel (java.util.Optional<MovieEntity> movieEntity) {
		
		logger.trace("Executing optionalToModel.");
		
		logger.info("Convertendo modelo opcional em modelo JSON.");
		
		Movie movie = new Movie();
		
		movie.setId(movieEntity.get().getId());
		movie.setTitle(movieEntity.get().getTitle());
		movie.setDirector(movieEntity.get().getDirector());
		movie.setWriters(movieEntity.get().getWriter());
		movie.setGender(movieEntity.get().getGender());
		movie.setRelease_date(movieEntity.get().getRelease_date());
		
		logger.info("Optional convertido para mododelo JSON.");
		return movie;
		
	}
	
	public List<Movie> listToModel(List<MovieEntity> moviesEntity) {
		
		logger.trace("Executing listToModel.");
		
		List<Movie> movies = new ArrayList<Movie>();
		
		logger.info("Percorrendo lista de filmes.");		
		for(MovieEntity movieEntity : moviesEntity) {
			movies.add(toModel(movieEntity));
		}
		
		return movies;
		
	}

}
