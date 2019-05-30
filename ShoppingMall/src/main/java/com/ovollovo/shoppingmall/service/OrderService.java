package com.ovollovo.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.order.Order;
import com.ovollovo.shoppingmall.order.dao.OrderMapper;

@Service
public class OrderService implements OrderServiceI {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderJson orderJson;
	
	@Override
	public void registerOrder(Order order) {
		orderMapper.registerOrder(order.getUserid(), order.getGoodscode(), order.getGoodscount(), order.getPrice(), order.getDelivery_info());
	}

	@Override
	public Order[] getOrderList(int page) {
		int LastPage= getOrderListLastPage(null);
		if (page > LastPage) {
			return orderMapper.searchOrderList((LastPage-1)*10,10);
		}
		return orderMapper.searchOrderList((page-1)*10,10);
	}

	@Override
	public Order[] getOrderList(String userid,int page) {
		int LastPage= getOrderListLastPage(userid);
		if (page > LastPage) {
			return orderMapper.searchOrderListById(userid,(LastPage-1)*10,10);
		}
		return orderMapper.searchOrderListById(userid,(page-1)*10,10);
	}
	
	@Override
	public int getCurrentMaxPage(int page) {
		int LastPage = getOrderListLastPage(null);
		if (((page-1)/10+1)*10 > LastPage) {
			return LastPage;
		}
		return ((page-1)/10+1)*10 ;
	}
	
	@Override
	public JsonObject registerShippingInfo(int code, String companyCode, String invoiceNumber) {
		orderMapper.registerShippingInfo(code, companyCode, invoiceNumber);
		return orderJson.getRegisterShippingInfoResultJson(code, companyCode, invoiceNumber);
	}

	private int getOrderListLastPage(String userid) {
		int count;
		if (userid != null) {
			count =orderMapper.getOrderListCountById(userid);
		}else {
			count =orderMapper.getOrderListCount();
		}
		int LastPage = 0;
		LastPage += (count/10);
		if (count%10 > 0) {
			LastPage++;
		}
		if (LastPage == 0) {
			LastPage = 1;
		}
		return LastPage;
	}

	@Override
	public JsonObject getDeliveryInfo(int code) {
		return orderJson.getDeliveryInfoResult(orderMapper.searchDeliveryInfo(code).split(","));
	}

}
