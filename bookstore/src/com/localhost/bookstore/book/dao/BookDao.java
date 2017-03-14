package com.localhost.bookstore.book.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.localhost.bookstore.book.domain.Book;
import com.localhost.bookstore.util.JDBCUtil;
import com.mysql.jdbc.PreparedStatement;

public class BookDao {
	/**
	 * 查询所有图书
	 * @return List<Book>
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Book> findAll() throws ClassNotFoundException, SQLException {
		List<Book> books = new ArrayList<Book>();
		
		String sql = "select * from bs_book";
		PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			//int bid, String bname, double price, String author,String image
			Book book = new Book();
			book.setBid(rs.getInt(1));
			book.setBname(rs.getString(2));
			book.setPrice(rs.getDouble(3));
			book.setAuthor(rs.getString(4));
			book.setImage(rs.getString(5));
			books.add(book);
		}
		return books;
	}
	
	/**
	 * 按照category查询
	 * @param cid
	 * @return List<Book>
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Book> findByCategory(int cid) throws ClassNotFoundException, SQLException {
		List<Book> books = new ArrayList<Book>();
		
		String sql = "select * from bs_book where cid=?";
		PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);
		pstmt.setInt(1, cid);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			//int bid, String bname, double price, String author,String image
			Book book = new Book();
			book.setBid(rs.getInt(1));
			book.setBname(rs.getString(2));
			book.setPrice(rs.getDouble(3));
			book.setAuthor(rs.getString(4));
			book.setImage(rs.getString(5));
			books.add(book);
		}
		return books;
	}

	/**
	 * 查看图书详细信息
	 * @param bid
	 * @return Book
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Book findByBid(int bid) throws ClassNotFoundException, SQLException {
		String sql = "select * from bs_book where bid=?";
		PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);
		pstmt.setInt(1, bid);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		Book book = new Book();
		book.setBid(rs.getInt(1));
		book.setBname(rs.getString(2));
		book.setPrice(rs.getDouble(3));
		book.setAuthor(rs.getString(4));
		book.setImage(rs.getString(5));
		return book;
	}
	
	/**
	 * 通过书名查找书籍
	 * @param name
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Book findByBookname(String name) throws ClassNotFoundException, SQLException {
		String sql = "select * from bs_book where bname=?";
		PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		Book book = new Book();
		book.setBid(rs.getInt(1));
		book.setBname(rs.getString(2));
		book.setPrice(rs.getDouble(3));
		book.setAuthor(rs.getString(4));
		book.setImage(rs.getString(5));
		return book;
	}
}
