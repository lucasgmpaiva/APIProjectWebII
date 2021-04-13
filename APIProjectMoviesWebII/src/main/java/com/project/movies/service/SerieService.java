package com.project.movies.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.movies.entity.SerieEntity;
import com.project.movies.mapper.SerieMapper;
import com.project.movies.model.Serie;
import com.project.movies.repository.SerieRepository;

@Service
public class SerieService {
	
	@Autowired
	SerieRepository serieRepository;
	
	@Autowired
	SerieMapper mapper;
	
	public Serie createSerie(Serie serie) {
		
		SerieEntity serieEntity = mapper.toEntity(serie);
		
		return mapper.toModel(serieRepository.save(serieEntity));
		
	}
	
	public List<Serie> findAll() {
		return mapper.listToModel(serieRepository.findAll());
	}
	
	public Serie findById(Long id) {
		return mapper.optionalToModel(serieRepository.findById(id));
	}

	public String deleteSerieById(Long id) {
		SerieEntity serieEntity = mapper.toEntity(findById(id));
		serieRepository.delete(serieEntity);
		return "Succeed!";
	}
	
	public Serie editSerie(Serie serie) {
		SerieEntity serieEntity = mapper.toEntity(serie);
		return mapper.toModel(serieRepository.save(serieEntity));
	}
	
	public List<Serie> searchSeries (String title, String broadcaster, String gender) {
		
		List<Serie> series = findAll();
		List<Serie> foundSeries = new ArrayList<Serie>();
		if(title != null || broadcaster != null || gender != null) {
			for (Serie serie : series) {
				if(title != null && broadcaster == null && gender == null) {
					if(serie.getTitle().toLowerCase().contains(title.toLowerCase()))
						foundSeries.add(serie);
				}
				if(broadcaster != null && title == null && gender == null) {
					if(serie.getBroadcaster().toLowerCase().contains(broadcaster.toLowerCase()))
						foundSeries.add(serie);
				}
				if(broadcaster != null && title != null && gender == null) {
					if(serie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& serie.getBroadcaster().toLowerCase().contains(broadcaster.toLowerCase()))
						foundSeries.add(serie);
				}
				if(broadcaster == null && title != null && gender != null) {
					if(serie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& serie.getGender().toLowerCase().contains(gender.toLowerCase()))
						foundSeries.add(serie);
				}
				if(broadcaster != null && title == null && gender != null) {
					if(serie.getBroadcaster().toLowerCase().contains(broadcaster.toLowerCase())
							&& serie.getGender().toLowerCase().contains(gender.toLowerCase()))
						foundSeries.add(serie);
				}
				if(gender != null && broadcaster == null && title == null) {
					if(serie.getGender().toLowerCase().contains(gender.toLowerCase()))
						foundSeries.add(serie);
				}
				if(broadcaster != null && title != null && gender != null) {
					if(serie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& serie.getGender().toLowerCase().contains(gender.toLowerCase())
							&& serie.getBroadcaster().toLowerCase().contains(broadcaster.toLowerCase()))
						foundSeries.add(serie);
				}
			}
		}
		
		return foundSeries;
		
	}
	
}
