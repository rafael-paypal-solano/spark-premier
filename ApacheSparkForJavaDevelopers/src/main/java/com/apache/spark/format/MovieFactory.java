package com.apache.spark.format;

import java.util.stream.Stream;

public class MovieFactory {
	
	public static Movie createMovie(String str, String delimiter ) {
		String[] fields = str.split(delimiter);
		
		if ( fields . length != 3) {
			System.out.println("The elements are ::");
			Stream.of( fields ).forEach(System. out ::println);
			throw new IllegalArgumentException("Each line must contain 3 fields while the current line has ::" + fields.length );
			
		}	
		
		Integer movieId = Integer.parseInt( fields [0]);
		String title = fields [1].trim();
		String genere = fields [2].trim();
		Movie movie = new Movie();
		
		movie.setGenre(genere);
		movie.setMovieId(movieId);
		movie.setTitle(title);
		
		return movie;
	}	
}
