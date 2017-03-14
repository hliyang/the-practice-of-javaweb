package com.localhost.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.localhost.bookstore.book.dao.BookDao;
import com.localhost.bookstore.book.domain.Book;
import com.localhost.bookstore.cart.domain.Cart;
import com.localhost.bookstore.cart.domain.CartItem;
import com.localhost.bookstore.util.BaseServlet;


@SuppressWarnings("serial")
public class CartServlet extends BaseServlet {

	/**
	 * 添加购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 获取书
		int bid = Integer.valueOf(request.getParameter("bid"));
		Book book = null;
		try {
			book = new BookDao().findByBid(bid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 获取书的数量
		int count = Integer.parseInt(request.getParameter("count"));
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		cart.add(cartItem);
		return "forward:/jsps/cart/list.jsp";
	}
	
	/**
	 * 清空购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((Cart)request.getSession().getAttribute("cart")).clear();
		return "forward:/jsps/cart/list.jsp";
	}
	
	/**
	 * 删除指定购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		int bid = Integer.valueOf(request.getParameter("bid"));
		cart.delete(bid);
		return "forward:/jsps/cart/list.jsp";
	}
}
