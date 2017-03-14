package com.localhost.bookstore.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * 获取connection和preparedStatement
 * @author hliyang
 *
 */
public class JDBCUtil {

	/**
	 * 返回connection
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/bookstore";
		return (Connection) DriverManager.getConnection(url, "root", "");
	}
	
	/**
	 * 返回PreparedStatement
	 * @param sql
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
		return (PreparedStatement) getConnection().prepareStatement(sql);
	}
}
