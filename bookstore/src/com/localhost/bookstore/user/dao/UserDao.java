package com.localhost.bookstore.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.localhost.bookstore.user.domain.User;
import com.localhost.bookstore.util.JDBCUtil;
import com.mysql.jdbc.PreparedStatement;

public class UserDao {

	/**
	 * 按用户名查询
	 * 
	 * @param username
	 * @return user
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public User findByUsername(String username)
			throws ClassNotFoundException, SQLException {
		String sql = "select * from bs_user where username=?";
		PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		
		// 设置user
		User user = null;
		while(rs.next()) {
			user = new User();
			user.setUid(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
		}
		return user;
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void add(User user) throws ClassNotFoundException, SQLException {
		String sql = "insert into bs_user values(null,?,?)";
		PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		pstmt.executeUpdate();
	}
}
