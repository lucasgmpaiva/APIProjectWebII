package com.project.movies.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.movies.entity.SerieEntity;
import com.project.movies.model.Serie;

@Component
public class SerieMapper {
	
	public Serie toModel(SerieEntity serieEntity) {
		
		Serie serie = new Serie();
		
		serie.setId(serieEntity.getId());
		serie.setTitle(serieEntity.getTitle());
		serie.setBroadcaster(serieEntity.getBroadcaster());
		serie.setGender(serieEntity.getGender());
		serie.setRelease_date(serieEntity.getRelease_date());
		
		return serie;
		
	}
	
	public SerieEntity toEntity(Serie serie) {
		
		SerieEntity serieEntity = new SerieEntity();
		
		serieEntity.setId(serie.getId());
		serieEntity.setTitle(serie.getTitle());
		serieEntity.setBroadcaster(serie.getBroadcaster());
		serieEntity.setGender(serie.getGender());
		serieEntity.setRelease_date(serie.getRelease_date());
		
		return serieEntity;
		
	}
	
	public Serie optionalToModel (java.util.Optional<SerieEntity> serieEntity) {
		
		Serie serie = new Serie();
		
		serie.setId(serieEntity.get().getId());
		serie.setTitle(serieEntity.get().getTitle());
		serie.setBroadcaster(serieEntity.get().getBroadcaster());
		serie.setGender(serieEntity.get().getGender());
		serie.setRelease_date(serieEntity.get().getRelease_date());
		
		return serie;
		
	}
	
	public List<Serie> listToModel(List<SerieEntity> seriesEntity) {
		
		List<Serie> series = new ArrayList<Serie>();
		
		for(SerieEntity serieEntity : seriesEntity) {
			series.add(toModel(serieEntity));
		}
		
		return series;
	}

}
