package com.localhost.bookstore.user.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.localhost.bookstore.cart.domain.Cart;
import com.localhost.bookstore.user.dao.UserDao;
import com.localhost.bookstore.user.domain.User;
import com.localhost.bookstore.util.BaseServlet;

public class UserServlet extends BaseServlet {
	private UserDao userDao = new UserDao();

	/**
	 * 注册功能
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest request,
			HttpServletResponse response) {

		// 封装表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User form = new User();
		form.setUsername(username);
		form.setPassword(password);

		// 注册用户
		try {
			userDao.add(form);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("msg", "恭喜，注册成功！请登录...");
		return "forward:/jsps/user/login.jsp";
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		// 封装表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User form = new User();
		form.setUsername(username);
		form.setPassword(password);

		User user = null;
		try {
			user = userDao.findByUsername(form.getUsername());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将用户添加到session中
		request.getSession().setAttribute("user", form);
		// ---------------------------------------------
		// 这里：在用户登录成功的时候就给用户一个购物车
		// 给用户分配购物车，即：添加session
		request.getSession().setAttribute("cart", new Cart());
		return "redirect:/index.jsp";
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		return "redirect:/index.jsp";
	}
}
