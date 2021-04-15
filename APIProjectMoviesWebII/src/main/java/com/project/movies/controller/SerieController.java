package com.project.movies.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.movies.model.Serie;
import com.project.movies.service.SerieService;

@RestController
@RequestMapping("/serie")
public class SerieController {
	
	Logger logger = LoggerFactory.getLogger(SerieController.class);
	
	@Autowired
	SerieService service;
	
	@PostMapping
	public Serie createSerie(@RequestBody Serie serie) {
		logger.info("Inicializando criação de série.");
		return service.createSerie(serie);
	}
	
	@GetMapping("/all")
	public List<Serie> getAllSeries() {
		logger.info("Inicializando listagem de todas as séries.");
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public Serie getById(@PathVariable("id") Long id) {
		logger.info("Inicializando busca de filme por id.");
		return service.findById(id);
	}
	
	@GetMapping("/search")
	public List<Serie> getByTitle(@RequestParam(value = "title", required = false) String title,
									@RequestParam(value = "broadcaster", required = false) String broadcaster,
									@RequestParam(value = "gender", required = false) String gender) {
		logger.info("Inicializando busca de série por parâmetros.");
		return service.searchSeries(title, broadcaster, gender);
	}
	
	@DeleteMapping("/{id}")
	public String deleteSerie(@PathVariable("id") Long id) {
		logger.info("Inicializando remoção de série.");
		return service.deleteSerieById(id);
	}
	
	@PutMapping("/{id}")
	public Serie editMovie(@PathVariable("id") Long id,
							@RequestBody Serie serie) {
		logger.info("Inicializando edição de série.");
		serie.setId(id);
		return service.editSerie(serie);		
	}

}
