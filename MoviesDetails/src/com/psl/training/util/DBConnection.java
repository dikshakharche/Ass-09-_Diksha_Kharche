package com.psl.training.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	static Connection con = null;

	private DBConnection() {
		super();
	}
	static String driverClass = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@//localhost:1521/XE";
	static String username = "db_user";
	static String password = "db_user";
	public static Connection getConnection() {
		try {
			Class.forName(driverClass);
			con = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public static void creatTables() 	{	
		try {
			Statement stmt=con.createStatement();
			stmt.execute("create table movies(movieId number(10) Primary Key, movieName varchar(100),movieType varchar(50),movielang varchar(30),releaseDate date, movierating number(15),totalBusinessDone number(10))");
		} catch (SQLException e) {
			System.out.println("table already exists");
		}
		
			try {
				Statement stmt=con.createStatement();
				stmt.execute("create table casting(actorName varchar(50),movieId number(10), foreign key(movieId) references movies(movieId))");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
