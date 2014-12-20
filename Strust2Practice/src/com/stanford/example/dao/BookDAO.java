package com.stanford.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stanford.example.model.Book;


public class BookDAO extends AbtractDAO {
	
	public static Book getBook(Integer id)  {
		return null;
	}

	public static Book getBookByName(String name)  {
		return null;
	}
	
	public static List<Book> getBookList(){
		return null;
	}
}
