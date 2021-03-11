package com.psl.training.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.psl.training.model.*;

public class Movies {
    //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	
	List<Movie> movies = new ArrayList<Movie>();
	
	public List<Movie> populateMovies(File f) throws IOException{
		
		try {
			BufferedReader bf=new BufferedReader(new FileReader(f));
			String thisLine;
			while((thisLine=bf.readLine())!=null) {
				String[] temp = thisLine.split(",");
				List<String> lst = Arrays.asList(temp[5].split("-"));
				Movie movieObj = new Movie(Integer.parseInt(temp[0]),temp[1],temp[2],temp[3],Date.valueOf(temp[4]),lst,Double.parseDouble(temp[6]),Double.parseDouble(temp[7]));
				movies.add(movieObj);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return movies;
	}
}
