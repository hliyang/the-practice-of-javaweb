package com.localhost.bookstore.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.localhost.bookstore.cart.domain.Cart;
import com.localhost.bookstore.cart.domain.CartItem;
import com.localhost.bookstore.order.dao.OrderDao;
import com.localhost.bookstore.order.domain.Order;
import com.localhost.bookstore.order.domain.OrderItem;
import com.localhost.bookstore.user.domain.User;
import com.localhost.bookstore.util.BaseServlet;

@SuppressWarnings("serial")
public class OrderServlet extends BaseServlet {
	private OrderDao orderDao = new OrderDao();
	
	/**
	 * 生成订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String create(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 从购物车中取出商品
		 */
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		/*
		 * 2. 创建Order
		 */
		Order order = new Order();
		order.setOrdertime(new Date());//下单时间
		User owner = (User)req.getSession().getAttribute("user");
		order.setOwner(owner);//设置订单所有者
		
		Collection<CartItem> cartItemList = cart.getCartItems();
		double total = 0;
		for(CartItem cartItem : cartItemList) {
			total += cartItem.getSubtotal();
		}
		order.setTotal(total);//设置总计
		
		/*
		 * 3. 创建List<OrderItem>
		 * 一个CartItem对应一个OrderItem
		 */
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(CartItem cartItem : cartItemList) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setBook(cartItem.getBook());
			orderItem.setOrder(order);
			orderItemList.add(orderItem);
		}
		order.setOrderItemList(orderItemList);
		
		/*
		 * 4. 调用service完成添加
		 */
		try {
			orderDao.add(order);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		// 删除购物车条目
		cart.clear();
		return "redirect:/OrderServlet?method=myOrders";
	}
	
	/**
	 * 我的订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myOrders(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/*
		 * 从当前session中获取User
		 */
		User user = (User)req.getSession().getAttribute("user");
		try {
			List<Order> orders = orderDao.findByUser(user);
			req.setAttribute("orderList", orders);
			return "forward:/jsps/order/list.jsp";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据orderId查找订单条目
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String oid = req.getParameter("oid");
		List<OrderItem> orderItemList;
		try {
			orderItemList = orderDao.load(oid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		req.setAttribute("orderItemList", orderItemList);
		
		return "forward:/jsps/order/desc.jsp";
	}
}
