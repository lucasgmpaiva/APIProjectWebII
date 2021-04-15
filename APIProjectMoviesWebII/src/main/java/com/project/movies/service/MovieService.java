package com.project.movies.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger logger = LoggerFactory.getLogger(MovieService.class);
	
	
	public Movie createMovie(Movie movie) {
		
		MovieEntity movieEntity = mapper.toEntity(movie);
		logger.info("Salvando filme no banco de dados.");
		return mapper.toModel(movieRepository.save(movieEntity));
		
	}

	public List<Movie> findAll() {
		logger.info("Realizando busca por todos os filmes...");
		return mapper.listToModel(movieRepository.findAll());
	}
	
	public Movie findById(Long id) {
		logger.info("Realizando busca por filme via id...");
		return mapper.optionalToModel(movieRepository.findById(id));
	}
	
	public String deleteMovieById(Long id) {
		logger.info("Buscando o filme pelo id...");
		MovieEntity movieEntity = mapper.toEntity(findById(id));
		logger.info("Deletando filme do banco de dados...");
		movieRepository.delete(movieEntity);
		return "Succeed!";
	}
	
	public Movie editMovie(Movie movie) {
		MovieEntity movieEntity = mapper.toEntity(movie);
		logger.info("Salvando filme com novas informações...");
		return mapper.toModel(movieRepository.save(movieEntity));
	}
	
	public List<Movie> searchMovies (String title, String director, String gender) {
		
		List<Movie> movies = findAll();
		List<Movie> foundMovies = new ArrayList<Movie>();
		if(title != null || director != null || gender != null) {
			for (Movie movie : movies) {
				if(title != null && director == null && gender == null) {
					logger.info("Identificado que a busca é por título");
					if(movie.getTitle().toLowerCase().contains(title.toLowerCase()))
						logger.info("+ 1 filme encontrado");
						foundMovies.add(movie);
				}
				if(director != null && title == null && gender == null) {
					logger.info("Identificado que a busca é por diretor");
					if(movie.getDirector().toLowerCase().contains(director.toLowerCase()))
						logger.info("+ 1 filme encontrado");
						foundMovies.add(movie);
				}
				if(director != null && title != null && gender == null) {
					logger.info("Identificado que a busca é por título e diretor");
					if(movie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& movie.getDirector().toLowerCase().contains(director.toLowerCase()))
						logger.info("+ 1 filme encontrado");
						foundMovies.add(movie);
				}
				if(director == null && title != null && gender != null) {
					logger.info("Identificado que a busca é por título e gênero");
					if(movie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& movie.getGender().toLowerCase().contains(gender.toLowerCase()))
						logger.info("+ 1 filme encontrado");
						foundMovies.add(movie);
				}
				if(director != null && title == null && gender != null) {
					logger.info("Identificado que a busca é por diretor e gênero");
					if(movie.getDirector().toLowerCase().contains(director.toLowerCase())
							&& movie.getGender().toLowerCase().contains(gender.toLowerCase()))
						logger.info("+ 1 filme encontrado");
						foundMovies.add(movie);
				}
				if(gender != null && director == null && title == null) {
					logger.info("Identificado que a busca é por gênero");
					if(movie.getGender().toLowerCase().contains(gender.toLowerCase()))
						logger.info("+ 1 filme encontrado");
						foundMovies.add(movie);
				}
				if(director != null && title != null && gender != null) {
					logger.info("Identificado que a busca é por título, diretor e gênero");
					if(movie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& movie.getGender().toLowerCase().contains(gender.toLowerCase())
							&& movie.getDirector().toLowerCase().contains(director.toLowerCase()))
						logger.info("+ 1 filme encontrado");
						foundMovies.add(movie);
				}
			}
		}
		logger.info("Enviando lista de filmes encontrados.");
		return foundMovies;
		
	}
	
}
