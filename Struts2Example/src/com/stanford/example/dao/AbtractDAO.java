package com.stanford.example.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbtractDAO {
	
	public static final String DATABASE_ADDRESS = "D:\\AssemblaSVN\\trunk\\BookStore\\db\\testdb";
	
	public static final String DATABASE_PARAMETER = ";readonly=true;ifexists=true";
	
	public static void closeStuff(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if(rs != null) {
			rs.close();
		}
		if(stmt != null) {
			stmt.close();
		}
		if(conn != null) {
			conn.close();
		}
	}
}
