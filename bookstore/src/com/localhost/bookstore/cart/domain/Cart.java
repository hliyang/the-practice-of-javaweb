package com.localhost.bookstore.cart.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车
 * 
 * @author hliyang
 * 
 */
public class Cart {
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();

	public double getTotal() {
		double total = 0; 
		for(CartItem cartItem : getCartItems()) {
			total += cartItem.getSubtotal();
		}
		return total;
	}

	/**
	 * 添加条目到车中
	 * 
	 * @param cartItem
	 */
	public void add(CartItem cartItem) {
		if (map.containsKey(cartItem.getBook().getBid())) {
			// 如果购物车已经包含这个条目了，在以前的基础上添加
			CartItem _cartItem = map.get(cartItem.getBook().getBid());
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
			map.put(cartItem.getBook().getBid(), _cartItem);
		} else {
			map.put(cartItem.getBook().getBid(), cartItem);
		}
	}

	/**
	 * 清空购物车
	 */
	public void clear() {
		map.clear();
	}

	/**
	 * 删除某一条目
	 * 
	 * @param bid
	 */
	public void delete(int bid) {
		map.remove(bid);
	}

	/**
	 * 获取所有条目
	 * 
	 * @return
	 */
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
}
