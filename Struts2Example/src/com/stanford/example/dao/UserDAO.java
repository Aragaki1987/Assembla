package com.stanford.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.stanford.example.model.User;

public class UserDAO extends AbtractDAO {

	public static User getUser(Integer id) throws Exception {
		User user = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");

			conn = DriverManager.getConnection(
					"jdbc:hsqldb:file:" + DATABASE_ADDRESS + DATABASE_PARAMETER, "SA", "");
			stmt = conn.createStatement();
			
			List<User> users = new ArrayList<User>();
			rs = stmt.executeQuery("select * from user where");
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt("ID"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));				
				users.add(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			closeStuff(conn, stmt, rs);
		}

		return user;
	}

	public static User getUserByName(String username) throws Exception {
		User user = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");

			conn = DriverManager.getConnection(
					"jdbc:hsqldb:file:" + DATABASE_ADDRESS + DATABASE_PARAMETER, "SA", "");
			stmt = conn.prepareStatement("select * from user where username = ?");
			stmt.setString(1, username);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt("ID"));
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
			}
		} catch (Exception e) {	
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			closeStuff(conn, stmt, rs);
		}
		return user;
	}
}
