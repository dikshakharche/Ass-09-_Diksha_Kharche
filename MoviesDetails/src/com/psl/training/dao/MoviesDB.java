package com.psl.training.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.psl.training.model.*;
import com.psl.training.model.Movie;
import com.psl.training.service.MoviesService;
import com.psl.training.util.DBConnection;

public class MoviesDB {
	Connection con = DBConnection.getConnection();

	public boolean addAllMoviesInDb(List<Movie> movies) {
		PreparedStatement pstmt = null;
		
		for(Movie m : movies) {
			try {
				pstmt = con.prepareStatement("insert into movies values (?,?,?,?,?,?,?)");
				pstmt.setInt(1, m.getMovieId());
				pstmt.setString(2, m.getMovieName());
				pstmt.setString(3, m.getMovieType().name());
				pstmt.setString(4, m.getLanguage().name());
				pstmt.setDate(5, m.getReleaseDate());
				pstmt.setDouble(6, m.getRating());
				pstmt.setDouble(7, m.getTotalBusinessDone());
				pstmt.executeUpdate();
				
				for(String s:m.getCasting()) {
				     pstmt = con.prepareStatement("insert into casting values (?,?)");
				     pstmt.setString(1,s);
				     pstmt.setInt(2, m.getMovieId());
				     pstmt.executeUpdate();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	return true;
	}
	
	public void addMovie(Movie m) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("insert into movies values (?,?,?,?,?,?,?)");
			pstmt.setInt(1, m.getMovieId());
			pstmt.setString(2, m.getMovieName());
			pstmt.setString(3, m.getMovieType().name());
			pstmt.setString(4, m.getLanguage().name());
			pstmt.setDate(5, m.getReleaseDate());
			pstmt.setDouble(6, m.getRating());
			pstmt.setDouble(7, m.getTotalBusinessDone());
			pstmt.executeUpdate();
			for(String s:m.getCasting()) {
				 pstmt = con.prepareStatement("insert into casting values (?,?)");
			     pstmt.setString(1,s);
			     pstmt.setInt(2, m.getMovieId());
			     pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateRatings(Movie movie, double rating) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("update movies set movierating = ? where movieId = ?");
			pstmt.setDouble(1, rating);
			pstmt.setInt(2, movie.getMovieId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void updateBusiness(Movie movie, double amount) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("update movies set totalbusinessdone = ? where movieId = ?");
			pstmt.setDouble(1, amount);
			pstmt.setInt(2, movie.getMovieId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

