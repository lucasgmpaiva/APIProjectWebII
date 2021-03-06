package com.project.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.movies.entity.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>{
	
	
}