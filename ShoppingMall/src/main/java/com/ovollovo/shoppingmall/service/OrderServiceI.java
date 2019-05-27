package com.ovollovo.shoppingmall.service;

import com.ovollovo.shoppingmall.order.Order;

public interface OrderServiceI {
	public void registerOrder(Order order);
	
	public Order[] getOrderList(int page);
	
	public Order[] getOrderList(String userid);
	
	public int getCurrentMaxPage(int page);
	
	public void registerShippingInfo(int code,String companyCode,String invoiceNumber);
}
