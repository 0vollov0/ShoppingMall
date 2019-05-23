package com.ovollovo.shoppingmall.goods.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ovollovo.shoppingmall.goods.Goods;
import com.ovollovo.shoppingmall.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	GoodsService goodsService;

	@ModelAttribute("contextPath")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@RequestMapping(value = "/goodsList", method = RequestMethod.GET)
	public String goodsList(Model model,@RequestParam("classification") int classification,@RequestParam("n") int n) {
		System.out.println(classification);
		int count;
		/*
		if (request.getParameter("n") == null) {
			return "/";
		}
		int n = Integer.parseInt(request.getParameter("n"));
		*/
		if (n < 1) {
			return "/";
		}
		//classification_1 = request.getParameter("classification_1");
		//classification_2 = request.getParameter("classification_2");
		
		//if (classification_2 == null) {
		/*
		if(false) {
			Goods[] goods = goodsService.getClassification_1Goods(classification_1,n);
			count = goodsService.getClassification_1Count(classification_1);
			if (count > 4*n) {
				model.addAttribute("nextNum", n+1);
			}
			if (n > 1) {
				model.addAttribute("prevNum", n-1);
			}
			model.addAttribute("goodsList", goods);
		}else {
			Goods[] goods = goodsService.getClassification_2Goods(classification_2,n);
			count = goodsService.getClassification_2Count(classification_2);
			if (count > 4*n) {
				model.addAttribute("nextNum", n+1);
			}
			if (n > 1) {
				model.addAttribute("prevNum", n-1);
			}
			model.addAttribute("goodsList", goods);
			model.addAttribute("category_2", goodsService.getCategory_2Name(classification_2));
		}*/
		//model.addAttribute("category_1", goodsService.getCategory_1Name(classification_1));
		return "goods/goodsList";
	}
}
