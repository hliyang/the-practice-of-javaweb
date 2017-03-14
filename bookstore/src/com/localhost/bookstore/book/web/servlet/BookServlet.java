package com.localhost.bookstore.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.localhost.bookstore.book.dao.BookDao;
import com.localhost.bookstore.util.BaseServlet;

public class BookServlet extends BaseServlet {
	private BookDao bookDao = new BookDao();
	
	/**
	 * 查询所有图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("bookList", bookDao.findAll());
			return "forward:/jsps/book/list.jsp";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按照category查询图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cid = Integer.valueOf(request.getParameter("cid")).intValue();
		try {
			request.setAttribute("bookList", bookDao.findByCategory(cid));
			return "forward:/jsps/book/list.jsp";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查看图书详细信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bid = Integer.valueOf(request.getParameter("bid")).intValue();
		try {
			request.setAttribute("book", bookDao.findByBid(bid));
			return "forward:/jsps/book/desc.jsp";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
