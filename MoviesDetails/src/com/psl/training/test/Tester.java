package com.psl.training.test;

import com.psl.training.util.DBConnection;

import java.io.File;
import java.io.IOException;

import com.psl.training.service.*;

public class Tester {

	public static void main(String[] args) throws IOException {
		DBConnection.getConnection();
		DBConnection.creatTables();
		MoviesService ms=new MoviesService();
		ms.populateMovies();
	}

}
