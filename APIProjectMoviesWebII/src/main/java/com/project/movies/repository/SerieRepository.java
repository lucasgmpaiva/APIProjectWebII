package com.project.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.movies.entity.SerieEntity;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Long> {

}
