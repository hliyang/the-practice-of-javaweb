package com.localhost.bookstore.order.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.localhost.bookstore.book.dao.BookDao;
import com.localhost.bookstore.order.domain.Order;
import com.localhost.bookstore.order.domain.OrderItem;
import com.localhost.bookstore.user.dao.UserDao;
import com.localhost.bookstore.user.domain.User;
import com.localhost.bookstore.util.JDBCUtil;
import com.mysql.jdbc.PreparedStatement;

public class OrderDao {
	UserDao userDao = new UserDao();
	BookDao bookDao = new BookDao();

	/**
	 * 添加订单
	 * 
	 * @param order
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void add(Order order) throws SQLException, ClassNotFoundException {
		User user = userDao.findByUsername(order.getOwner().getUsername());
		// 订单编号
		String oid = UUID.randomUUID().toString();
		/*
		 * 1. 插入订单
		 */
		String sql = "insert into bs_order values(?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = JDBCUtil.getPreparedStatement(sql);
		
			pstmt.setString(1, oid);
			pstmt.setDate(2, new java.sql.Date(order.getOrdertime().getTime()));
			pstmt.setDouble(3, order.getTotal());
			// 设置订单的拥有者
			pstmt.setInt(4, user.getUid());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		/*
		 * 2. 循环遍历订单的所有条目,完成插入订单条目
		 */
		sql = "insert into bs_orderitem values(null,?,?,?,?)";
		// 获取购物车中条目数量
		int len = order.getOrderItemList().size();
		for (int i = 0; i < len; i++) {
			// 获取每一个条目
			OrderItem item = order.getOrderItemList().get(i);
			try {
				PreparedStatement pstmt1 = JDBCUtil.getPreparedStatement(sql);
				pstmt1.setInt(1, item.getCount());
				pstmt1.setDouble(2, item.getSubtotal());
				pstmt1.setString(3, oid);
				// 设置书
				pstmt1.setInt(4,
						bookDao.findByBookname(item.getBook().getBname())
								.getBid());
				pstmt1.executeUpdate();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 按用户查询订单
	 * 
	 * @param uid
	 * @param pc
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Order> findByUser(User user) throws SQLException,
			ClassNotFoundException {
		// 获取uid
		int uid = userDao.findByUsername(user.getUsername()).getUid();
		List<Order> orderList = new ArrayList<Order>();
		Order order = null;
		String sql = "select * from bs_order where uid=?";
		PreparedStatement pstmt;
		try {
			pstmt = JDBCUtil.getPreparedStatement(sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pstmt.setInt(1, uid);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			order = new Order();
			order.setOid(rs.getString(1));
			order.setOrdertime(rs.getDate(2));
			order.setTotal(rs.getDouble(3));

			// 加载订单条目
			loadOrderItem(order);
			orderList.add(order);
		}

		return orderList;
	}

	/*
	 * 为指定的order载它的所有OrderItem
	 */
	private void loadOrderItem(Order order) throws SQLException,
			ClassNotFoundException {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		OrderItem orderItem = null;
		/*
		 * 1. 给sql语句select * from bs_orderitem where oid=? 2.
		 * 执行之，得到List<OrderItem> 3. 设置给Order对象
		 */
		String sql = "select * from bs_orderitem where oid=?";
		PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);
		pstmt.setString(1, order.getOid());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			orderItem = new OrderItem();
			orderItem.setIid(rs.getInt(1));
			orderItem.setCount(rs.getInt(2));
			orderItem.setSubtotal(rs.getDouble(3));
			orderItemList.add(orderItem);
		}
		order.setOrderItemList(orderItemList);
	}

	/**
	 * 根据orderId加载订单条目
	 * @param oid
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<OrderItem> load(String oid) throws ClassNotFoundException, SQLException {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		OrderItem orderItem = null;
		String sql = "select * from bs_orderitem where oid=?";
		PreparedStatement pstmt = JDBCUtil.getPreparedStatement(sql);
		pstmt.setString(1, oid);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			orderItem = new OrderItem();
			orderItem.setIid(rs.getInt(1));
			orderItem.setCount(rs.getInt(2));
			orderItem.setSubtotal(rs.getDouble(3));
			orderItem.setBook(bookDao.findByBid(rs.getInt(5)));
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}
}
