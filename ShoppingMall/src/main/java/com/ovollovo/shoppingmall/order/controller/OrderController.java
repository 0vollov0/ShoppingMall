package com.ovollovo.shoppingmall.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ovollovo.shoppingmall.goods.dao.GoodsMapper;
import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.member.ShoppingBasket;
import com.ovollovo.shoppingmall.order.DeliveryInfo;
import com.ovollovo.shoppingmall.order.Order;
import com.ovollovo.shoppingmall.service.GoodsService;
import com.ovollovo.shoppingmall.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;

	@ModelAttribute("contextPath")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@RequestMapping(value = "requestOrder", method = RequestMethod.POST)
	public String order(@ModelAttribute("deliveryInfo") DeliveryInfo deliveryInfo,HttpSession session) {
		Map<String, ShoppingBasket> shoppingBasketList = (Map<String, ShoppingBasket>) session.getAttribute("shoppingBasketList");
		Member member = (Member) session.getAttribute("member");
		int price=0;
		if (shoppingBasketList.size() <= 0) {
			return "redirect:/";
		}
		if (member == null) {
			return "redirect:/";
		}
		String goodscode = "";
		String goodscount = "";
		
        for (String key : shoppingBasketList.keySet()) {
        	int sale_count = shoppingBasketList.get(key).getCount();
        	goodsService.pushSaleCount(key, sale_count);
        	goodscode = goodscode + key +",";
        	goodscount = goodscount + sale_count+",";
        	price = price + (sale_count*shoppingBasketList.get(key).getGoods().getPrice());
		}
        goodscode = goodscode.substring(0,goodscode.length()-1);
        goodscount = goodscount.substring(0,goodscount.length()-1);
        
        Order order = new Order();
        order.setUserid(member.getId());
        order.setDelivery_info(deliveryInfo);
        order.setGoodscode(goodscode);
        order.setGoodscount(goodscount);
        order.setPrice(price);
        orderService.registerOrder(order);
		return "redirect:/";
	}
}
