package com.ovollovo.shoppingmall.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.member.ShoppingBasket;
import com.ovollovo.shoppingmall.order.DeliveryInfo;
import com.ovollovo.shoppingmall.order.Order;
import com.ovollovo.shoppingmall.order.dao.OrderMapper;

@Service
public class OrderService implements OrderServiceI {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderJson orderJson;
	
	@Autowired
	private GoodsService goodsService;
	
	@Override
	public void registerOrder(Order order) {
		orderMapper.registerOrder(order.getUserid(), order.getGoodscode(), order.getGoodscount(),order.getGoodsname() ,order.getPrice(), order.getDelivery_info());
	}
	
	@Override
	public JsonObject registerOrder(HttpSession session,DeliveryInfo deliveryInfo) {
		Map<String, ShoppingBasket> shoppingBasketList = (Map<String, ShoppingBasket>) session.getAttribute("shoppingBasketList");
		Member member = (Member) session.getAttribute("member");
		int price=0;
		if (shoppingBasketList == null || shoppingBasketList.size() <= 0) {
			return orderJson.getOrderResultJson(6);
		}
		if (member == null) {
			return orderJson.getOrderResultJson(7);
		}
		
		int emptyField = deliveryInfo.getEmptyField();
		System.out.println(deliveryInfo);
		if (emptyField != 0) {
			return orderJson.getOrderResultJson(emptyField);
		}
		
		String goodscode = "";
		String goodscount = "";
		String goodsname = "";
		
		for (String key : shoppingBasketList.keySet()) {
        	int sale_count = shoppingBasketList.get(key).getCount();
        	String name = shoppingBasketList.get(key).getGoods().getName();
        	goodsService.pushSaleCount(key, sale_count);
        	goodsService.decreaseStock(key, sale_count);
        	goodscode = goodscode + key +",";
        	goodscount = goodscount + sale_count+",";
        	goodsname += name + ",";
        	price = price + (sale_count*shoppingBasketList.get(key).getGoods().getPrice());
		}
        goodscode = goodscode.substring(0,goodscode.length()-1);
        goodscount = goodscount.substring(0,goodscount.length()-1);
        goodsname = goodsname.substring(0,goodsname.length() - 1);
        orderMapper.registerOrder(member.getId(), goodscode, goodscount,goodsname ,price, deliveryInfo.toString());
        session.removeAttribute("shoppingBasketList");
		return orderJson.getOrderResultJson(0);
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
	public int getCurrentMaxPage(int page,String userid) {
		int LastPage = getOrderListLastPage(userid);
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
