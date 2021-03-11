package com.psl.training.service;

import com.psl.training.model.*;
import com.psl.training.enums.*;
import com.psl.training.dao.MoviesDB;
import  com.psl.training.model.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.psl.training.model.*;

public class MoviesService {
	Movies moviesObj = new Movies();
	MoviesDB moviesDBObj = new MoviesDB();
	
	public List<Movie> populateMovies() throws IOException{
		List<Movie> moviesList=moviesObj.populateMovies(new File(".//movies.txt"));
		//moviesDBObj.addAllMoviesInDb(moviesList);
		return moviesList;
	}
	public void addMovie(Movie movie) {
		moviesObj.addMovie(movie);
		moviesDBObj.addMovie(movie);
	}
	public void serializeMovies( String fileName) {
		List<Movie> movies = moviesObj.getMovies();
		//File file = new File(fileName);
		
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(movies);
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Movie> deserializeMovie(String filename){
		List<Movie> movies = new ArrayList<>();
		//File file = new File(filename);
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			movies = (List<Movie>) ois.readObject();
			System.out.println(movies.size());
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	public List<Movie> getMoviesRealeasedInYear(int year){
		List<Movie> movies = moviesObj.getMovies();
		List <Movie> moviesReleasedInYear = new ArrayList<>();
		for(Movie m:movies) {
			LocalDate date = m.getReleaseDate().toLocalDate();
			int yr=date.getYear();
			if(yr == year)
				moviesReleasedInYear.add(m);
		}
		return moviesReleasedInYear;
	}
	public List<Movie> getMoviesByActor(String...actorNames){
		List<Movie> movies=moviesObj.getMovies();
		List<Movie> moviesByActor =  new ArrayList<>();
		for(Movie m:movies) {
			for(String actor:actorNames) {
				if(m.getCasting().contains(actor)) {
					moviesByActor.add(m);
					break;
				}
			}
		}
		return moviesByActor;
	}
	public void updateRatings(Movie movie,double rating) {
		List<Movie> movies=moviesObj.getMovies();

		if(movies.contains(movie)) {
			movie.setRating(rating);
			moviesDBObj.updateRatings(movie, rating);
			System.out.println("Rating Updated");
		}
		else {
			System.out.println("Movie does not exist in the list.");
		}
	}
	public void updateBusiness(Movie movie, double amount) {
		List<Movie> movies=moviesObj.getMovies();
		if(movies.contains(movie)) {
			movie.setTotalBusinessDone(amount);
			moviesDBObj.updateBusiness(movie, amount);
			System.out.println("Business Updated");
		}
		else {
			System.out.println("Movie does not exist in the list.");
		}
	}
	public Map<Language,Set<Movie>> businessDone(double amount){
		List<Movie> movies=moviesObj.getMovies();
		Set <Movie> movieSet = new TreeSet<>();
		Map <Language,Set<Movie>> movieMap = new HashMap<>();
		for(Movie movie:movies) {
			if(movie.getTotalBusinessDone() > amount) {
				movieSet.add(movie);
				if(movieMap.containsKey(movie.getLanguage())) {
					movieMap.get(movie.getLanguage()).add(movie);
				}
				else {
					movieMap.put(movie.getLanguage(), movieSet);
				}
			}
		}
		

		return movieMap;
	}

}
