package com.ovollovo.shoppingmall.service;

import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.order.DeliveryInfo;
import com.ovollovo.shoppingmall.order.Order;

public interface OrderServiceI {
	public void registerOrder(Order order);
	
	public JsonObject registerOrder(HttpSession session,DeliveryInfo deliveryInfo);
	
	public Order[] getOrderList(int page);
	
	public Order[] getOrderList(String userid,int page);
	
	public int getCurrentMaxPage(int page,String userid);
	
	public JsonObject registerShippingInfo(int code,String companyCode,String invoiceNumber);
	
	public JsonObject getDeliveryInfo(int code);
}
