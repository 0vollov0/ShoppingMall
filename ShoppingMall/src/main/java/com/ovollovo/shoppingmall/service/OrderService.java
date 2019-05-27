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
	public Order[] getOrderList(int page) {
		int LastPage= getOrderListLastPage();
		if (page > LastPage) {
			return orderMapper.searchOrderList((LastPage-1)*10,LastPage*10-1);
		}
		return orderMapper.searchOrderList((page-1)*10,page*10-1);
	}

	@Override
	public Order[] getOrderList(String userid) {
		return orderMapper.searchOrderListById(userid);
	}
	
	@Override
	public int getCurrentMaxPage(int page) {
		int LastPage = getOrderListLastPage();
		if (((page-1)/10+1)*10 > LastPage) {
			return LastPage;
		}
		return ((page-1)/10+1)*10 ;
	}
	
	@Override
	public void registerShippingInfo(int code, String companyCode, String invoiceNumber) {
		orderMapper.registerShippingInfo(code, companyCode, invoiceNumber);
	}

	public int getOrderListLastPage() {
		int count =orderMapper.getOrderListCount();
		int LastPage = 0;
		LastPage += (count/10);
		if (count%10 > 0) {
			LastPage++;
		}
		return LastPage;
	}

}
