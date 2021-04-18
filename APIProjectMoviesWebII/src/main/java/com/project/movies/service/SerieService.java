package com.project.movies.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	Logger logger = LoggerFactory.getLogger(SerieService.class);
	
	public Serie createSerie(Serie serie) {
		
		logger.trace("Executing createSerie.");
		
		SerieEntity serieEntity = mapper.toEntity(serie);
		logger.info("Salvando série no banco de dados.");
		return mapper.toModel(serieRepository.save(serieEntity));
		
	}
	
	public List<Serie> findAll() {
		
		logger.trace("Executing findAll.");
		
		logger.info("Realizando busca por todas as séries...");
		return mapper.listToModel(serieRepository.findAll());
	}
	
	public Serie findById(Long id) {
		
		logger.trace("Executing findById.");
		
		logger.info("Realizando busca de filme por id...");
		return mapper.optionalToModel(serieRepository.findById(id));
	}

	public String deleteSerieById(Long id) {
		
		logger.trace("Executing deleteSerieById.");
		
		logger.info("Buscando o filme pelo id...");
		SerieEntity serieEntity = mapper.toEntity(findById(id));
		logger.info("Realizando remoção de série...");
		serieRepository.delete(serieEntity);
		return "Succeed!";
	}
	
	public Serie editSerie(Serie serie) {
		
		logger.trace("Executing editSerie.");
		
		SerieEntity serieEntity = mapper.toEntity(serie);
		logger.info("Salvando filme com novas informações...");
		return mapper.toModel(serieRepository.save(serieEntity));
	}
	
	public List<Serie> searchSeries (String title, String broadcaster, String gender) {
		
		logger.trace("Executing searchSeries.");
		
		logger.info("Inicializando verificação de filtros...");
		
		List<Serie> series = findAll();
		List<Serie> foundSeries = new ArrayList<Serie>();
		if(title != null || broadcaster != null || gender != null) {
			for (Serie serie : series) {
				if(title != null && broadcaster == null && gender == null) {
					logger.info("Identificado que a busca é por título");
					if(serie.getTitle().toLowerCase().contains(title.toLowerCase())) {
						logger.info("+ 1 série encontrada");
						foundSeries.add(serie);
					}
				}
				if(broadcaster != null && title == null && gender == null) {
					logger.info("Identificado que a busca é por emissora");
					if(serie.getBroadcaster().toLowerCase().contains(broadcaster.toLowerCase())) {
						logger.info("+ 1 série encontrada");
						foundSeries.add(serie);
					}
				}
				if(broadcaster != null && title != null && gender == null) {
					logger.info("Identificado que a busca é por título e emissora");
					if(serie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& serie.getBroadcaster().toLowerCase().contains(broadcaster.toLowerCase())) {
						logger.info("+ 1 série encontrada");
						foundSeries.add(serie);
					}
				}
				if(broadcaster == null && title != null && gender != null) {
					logger.info("Identificado que a busca é por título e gênero");
					if(serie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& serie.getGender().toLowerCase().contains(gender.toLowerCase())) {
						logger.info("+ 1 série encontrada");
						foundSeries.add(serie);
					}
				}
				if(broadcaster != null && title == null && gender != null) {
					logger.info("Identificado que a busca é por emissora e gênero");
					if(serie.getBroadcaster().toLowerCase().contains(broadcaster.toLowerCase())
							&& serie.getGender().toLowerCase().contains(gender.toLowerCase())) {
						logger.info("+ 1 série encontrada");
						foundSeries.add(serie);
					}
				}
				if(gender != null && broadcaster == null && title == null) {
					logger.info("Identificado que a busca é por gênero");
					if(serie.getGender().toLowerCase().contains(gender.toLowerCase())) {
						logger.info("+ 1 série encontrada");
						foundSeries.add(serie);
					}
				}
				if(broadcaster != null && title != null && gender != null) {
					logger.info("Identificado que a busca é por título, emissora e gênero");
					if(serie.getTitle().toLowerCase().contains(title.toLowerCase())
							&& serie.getGender().toLowerCase().contains(gender.toLowerCase())
							&& serie.getBroadcaster().toLowerCase().contains(broadcaster.toLowerCase())) {
						logger.info("+ 1 série encontrada");
						foundSeries.add(serie);
					}
				}
			}
		}
		logger.info("Enviando lista de séries encontradas.");
		return foundSeries;
		
	}
	
}
