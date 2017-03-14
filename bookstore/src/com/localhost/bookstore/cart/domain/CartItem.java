package com.localhost.bookstore.cart.domain;

import com.localhost.bookstore.book.domain.Book;

/**
 * 购物车的条目
 * @author hliyang
 *
 */
public class CartItem {
	private Book book;
	private int count;

	/**
	 * 获取购物车条目的总价格
	 * @return
	 */
	public double getSubtotal() {
		return book.getPrice() * count;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
