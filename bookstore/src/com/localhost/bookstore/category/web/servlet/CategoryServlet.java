package com.localhost.bookstore.category.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.localhost.bookstore.category.dao.CategoryDao;
import com.localhost.bookstore.util.BaseServlet;

@SuppressWarnings("serial")
public class CategoryServlet extends BaseServlet {
	private CategoryDao categoryDao = new CategoryDao();
	
	/**
	 * 查询所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("categoryList", categoryDao.findAll());
			return "forward:/jsps/left.jsp";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
