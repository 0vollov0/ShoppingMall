package com.ovollovo.shoppingmall.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.member.ShoppingBasket;
import com.ovollovo.shoppingmall.order.DeliveryInfo;
import com.ovollovo.shoppingmall.order.Order;
import com.ovollovo.shoppingmall.service.GoodsService;
import com.ovollovo.shoppingmall.service.OrderJson;
import com.ovollovo.shoppingmall.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderJson orderJson;

	@ModelAttribute("contextPath")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@RequestMapping(value = "requestOrder", method = RequestMethod.POST)
	public @ResponseBody JsonObject order(@ModelAttribute("deliveryInfo") DeliveryInfo deliveryInfo,HttpSession session) {
		return orderService.registerOrder(session, deliveryInfo);
	}
	
	@RequestMapping(value = "/registerShippingInfo", method = RequestMethod.POST)
	public @ResponseBody JsonObject registerShippingInfo(@RequestParam("code") int code,
			@RequestParam("companyCode") String companyCode, @RequestParam("invoiceNumber") String invoiceNumber) {
		return orderService.registerShippingInfo(code, companyCode, invoiceNumber);
	}
	
	@RequestMapping(value = "/getDeliveryInfo", method = RequestMethod.POST)
	public @ResponseBody JsonObject getShippingInfo(@RequestParam("code") int code) {
		return orderService.getDeliveryInfo(code);
	}
}
