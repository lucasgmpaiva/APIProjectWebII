package com.project.movies.model;

import java.io.Serializable;

public class Movie implements Serializable{
		
	public static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private String director;
	private String writer;
	private String release_date;
	private String gender;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriters(String writer) {
		this.writer = writer;
	}
	
	public String getRelease_date() {
		return release_date;
	}
	
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}

}
