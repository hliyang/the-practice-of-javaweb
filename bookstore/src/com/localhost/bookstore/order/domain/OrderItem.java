package com.localhost.bookstore.order.domain;

import com.localhost.bookstore.book.domain.Book;

/**
 * 订单条目类
 * 
 * @author houliyang
 * 
 */
public class OrderItem {
	private int iid;
	private int count; // 单种商品数量
	private double subtotal; // 单种商品价格
	private Order order; // 订单
	private Book book; // 购买书籍

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
