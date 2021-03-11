package com.psl.training.service;

import com.psl.training.model.*;

import com.psl.training.dao.CastingDB;
import com.psl.training.dao.MoviesDB;
import  com.psl.training.model.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.psl.training.model.*;

public class MoviesService {
	Movies moviesObj = new Movies();
	MoviesDB moviesDBObj = new MoviesDB();
	
	public void populateMovies() throws IOException{
		List<Movie> moviesList=moviesObj.populateMovies(new File(".//movies.txt"));
		//moviesDBObj.addAllMoviesInDb(moviesList);
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
	
	
}
