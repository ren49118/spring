package com.mmit.jpit.api.controller;

import java.util.ArrayList;
import java.util.List;

public class RequestOrderDto {

	private ReceiverDto receiver;
	private List<OrderItemDto> orderItems = new ArrayList<OrderItemDto>();

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}

	public ReceiverDto getReceiver() {
		return receiver;
	}

	public void setReceiver(ReceiverDto receiver) {
		this.receiver = receiver;
	}
	
}
class ReceiverDto{
	private String name;
	private String phone;
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
class OrderItemDto{
	private int productId;
	private int qty;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
