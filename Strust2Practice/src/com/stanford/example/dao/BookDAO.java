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
	
	public static Book getBook(Integer id) throws Exception {
		Book book = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");

			conn = DriverManager.getConnection(
					"jdbc:hsqldb:file:" + DATABASE_ADDRESS + DATABASE_PARAMETER, "SA", "");
			stmt = conn.prepareStatement("select * from book where id = ?");
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				book = new Book();
				book.setId(rs.getInt("ID"));
				book.setName(rs.getString("NAME"));
				book.setPrice(rs.getInt("PRICE"));
				book.setAuthor(rs.getString("AUTHOR"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			closeStuff(conn, stmt, rs);
		}

		return book;
	}

	public static Book getBookByName(String name) throws Exception {
		Book book = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");

			conn = DriverManager.getConnection(
					"jdbc:hsqldb:file:" + DATABASE_ADDRESS + DATABASE_PARAMETER, "SA", "");
			stmt = conn.prepareStatement("select * from book where name = ?");
			stmt.setString(1, name);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				book = new Book();
				book.setId(rs.getInt("ID"));
				book.setName(rs.getString("NAME"));
				book.setPrice(rs.getInt("PRICE"));
				book.setAuthor(rs.getString("AUTHOR"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			closeStuff(conn, stmt, rs);
		}

		return book;
	}
	
	public static List<Book> getBookList() throws Exception {
		List<Book> results = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");

			conn = DriverManager.getConnection(
					"jdbc:hsqldb:file:" + DATABASE_ADDRESS + DATABASE_PARAMETER, "SA", "");
			stmt = conn.prepareStatement("select * from book");
			rs = stmt.executeQuery();
			results = new ArrayList<Book>();
			while(rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("ID"));
				book.setName(rs.getString("NAME"));
				book.setPrice(rs.getInt("PRICE"));
				book.setAuthor(rs.getString("AUTHOR"));
				results.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
			throw new ClassNotFoundException(e.getMessage());
		} finally {
			closeStuff(conn, stmt, rs);
		}
		
		return results;
	}
}
