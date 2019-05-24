package com.ovollovo.shoppingmall.service;

import com.ovollovo.shoppingmall.order.Order;

public interface OrderServiceI {
	public void registerOrder(Order order);
	
	public Order[] getOrderList();
	
	public Order[] getOrderList(String userid);
	
	public void registerShippingInfo(int code,String companyCode,String invoiceNumber);
}
