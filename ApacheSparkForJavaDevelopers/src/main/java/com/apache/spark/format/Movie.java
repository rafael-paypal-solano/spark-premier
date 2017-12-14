package com.apache.spark.format;

import java.io.Serializable;
import java.util.stream.Stream;

public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer movieId;
	private String title;
	private String genre;
	
	
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "Movie [movieId='" + movieId + "', title='" + title + "', genre='" + genre + "']";
	}

	
}
