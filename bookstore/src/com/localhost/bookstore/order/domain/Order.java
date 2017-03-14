package com.localhost.bookstore.order.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.localhost.bookstore.user.domain.User;

/**
 * 订单类
 * 
 * @author houliyang
 * 
 */
public class Order {
	private String oid; // 订单编号
	private Date ordertime;// 下单时间
	private double total;// 合计
	private User owner;// 订单所有者

	private List<OrderItem> orderItemList = new ArrayList<OrderItem>(); // 订单条目

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
