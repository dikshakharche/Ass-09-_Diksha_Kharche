package com.psl.training.service;

import com.psl.training.dao.CastingDB;
import com.psl.training.dao.MoviesDB;
import  com.psl.training.model.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.psl.training.model.*;

public class MoviesService {
	Movies moviesObj = new Movies();
	MoviesDB moviesDBObj = new MoviesDB();
	
	public void populateMovies() throws IOException{
		List<Movie> moviesList=moviesObj.populateMovies(new File(".//movies.txt"));
		moviesDBObj.addAllMoviesInDb(moviesList);
	}

}
