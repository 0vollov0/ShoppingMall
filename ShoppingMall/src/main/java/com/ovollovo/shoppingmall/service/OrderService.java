package com.ovollovo.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ovollovo.shoppingmall.order.Order;
import com.ovollovo.shoppingmall.order.dao.OrderMapper;

@Service
public class OrderService implements OrderServiceI {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public void registerOrder(Order order) {
		orderMapper.registerOrder(order.getUserid(), order.getGoodscode(), order.getGoodscount(), order.getPrice(), order.getDelivery_info());
	}

	@Override
	public Order[] getOrderList() {
		return orderMapper.searchOrderList();
	}

	@Override
	public Order[] getOrderList(String userid) {
		return orderMapper.searchOrderListById(userid);
	}

	@Override
	public void registerShippingInfo(int code, String companyCode, String invoiceNumber) {
		orderMapper.registerShippingInfo(code, companyCode, invoiceNumber);
	}

}
