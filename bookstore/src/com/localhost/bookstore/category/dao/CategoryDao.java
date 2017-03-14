package com.localhost.bookstore.category.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.localhost.bookstore.category.domain.Category;
import com.localhost.bookstore.util.JDBCUtil;
import com.mysql.jdbc.PreparedStatement;

public class CategoryDao {

	/**
	 * 查询所有分类
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Category> findAll() throws ClassNotFoundException, SQLException {
		List<Category> categories = new ArrayList<Category>();
		
		String sql = "select * from bs_category";
		PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Category category = new Category();
			category.setCid(rs.getInt(1));
			category.setCname(rs.getString(2));
			categories.add(category); // 获取name
		}
		return categories;
	}
}
