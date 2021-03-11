package com.psl.training.test;

import com.psl.training.util.DBConnection;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.psl.training.enums.Language;
import com.psl.training.model.Movie;
import com.psl.training.model.Movies;
import com.psl.training.service.*;

public class Tester {

	public static void main(String[] args) throws IOException {
		//DBConnection.getConnection();
		//DBConnection.creatTables();
		Movies movies=new Movies();
		MoviesService ms=new MoviesService();
	
		List<Movie> m=ms.populateMovies();
//		ms.serializeMovies("object.ser");
//		ms.deserializeMovie("object.ser");
//		for(Movie m:ms.getMoviesRealeasedInYear(2012)) {
//			System.out.println(m.getMovieName());
//		}
//		for(Movie m:ms.getMoviesByActor("Aamir Khan")) {
//			System.out.println(m.getMovieName());
//		}
	
		//ms.updateRatings(m.get(0), 9);
		//ms.updateBusiness(m.get(0), 120);
//		Map<Language,Set<Movie>> langMovie=ms.businessDone(1);
//		for (Entry<Language, Set<Movie>> entry : langMovie.entrySet())  
//            System.out.println("Key = " + entry.getKey() + 
//                             ", Value = " + entry.getValue()); 
	}

}
