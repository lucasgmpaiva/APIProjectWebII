package com.project.movies.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.project.movies.entity.SerieEntity;
import com.project.movies.model.Serie;

@Component
public class SerieMapper {
	
	Logger logger = LoggerFactory.getLogger(SerieMapper.class);
	
	public Serie toModel(SerieEntity serieEntity) {
		
		logger.info("Convertendo série em modelo para JSON.");	
		
		Serie serie = new Serie();
		
		serie.setId(serieEntity.getId());
		serie.setTitle(serieEntity.getTitle());
		serie.setBroadcaster(serieEntity.getBroadcaster());
		serie.setGender(serieEntity.getGender());
		serie.setRelease_date(serieEntity.getRelease_date());
		
		logger.info("Série convertida em modelo para JSON.");
		return serie;
		
	}
	
	public SerieEntity toEntity(Serie serie) {
		
		logger.info("Convertendo série em entidade do banco de dados.");
		
		SerieEntity serieEntity = new SerieEntity();
		
		serieEntity.setId(serie.getId());
		serieEntity.setTitle(serie.getTitle());
		serieEntity.setBroadcaster(serie.getBroadcaster());
		serieEntity.setGender(serie.getGender());
		serieEntity.setRelease_date(serie.getRelease_date());
		
		logger.info("Série convertida em entidade do banco de dados.");
		
		return serieEntity;
		
	}
	
	public Serie optionalToModel (java.util.Optional<SerieEntity> serieEntity) {
		
		logger.info("Convertendo modelo opcional em modelo JSON.");
		
		Serie serie = new Serie();
		
		serie.setId(serieEntity.get().getId());
		serie.setTitle(serieEntity.get().getTitle());
		serie.setBroadcaster(serieEntity.get().getBroadcaster());
		serie.setGender(serieEntity.get().getGender());
		serie.setRelease_date(serieEntity.get().getRelease_date());
		
		logger.info("Optional convertido para mododelo JSON.");
		return serie;
		
	}
	
	public List<Serie> listToModel(List<SerieEntity> seriesEntity) {
		
		List<Serie> series = new ArrayList<Serie>();
		
		logger.info("Percorrendo lista de séries.");
		for(SerieEntity serieEntity : seriesEntity) {
			series.add(toModel(serieEntity));
		}
		
		return series;
	}

}
