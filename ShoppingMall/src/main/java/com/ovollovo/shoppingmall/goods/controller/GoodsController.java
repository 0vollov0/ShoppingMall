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
	public String goodsList(Model model,@RequestParam("category") int category,@RequestParam("n") int n) {
		if (n < 1) {
			return "/";
		}
		Goods[] goods;
		int count;
		if (category % 100 == 0) {
			goods = goodsService.getCategoryFirstGoods(category, n);
			count =  goodsService.getCategoryFirstCount(category);
		}else {
			goods = goodsService.getCategorySecondGoods(category, n);
			count = goodsService.getCategorySecondCount(category);
			model.addAttribute("category_2",goodsService.getCategoryName(category));
		}
		System.out.println(count);
		model.addAttribute("category_1",goodsService.getCategoryName(category/100*100));
		model.addAttribute("category",category);
		if (count > 4*n) {
			model.addAttribute("next", n+1);
		}
		if (n > 1) {
			model.addAttribute("prev", n-1);
		}
		model.addAttribute("goodsList", goods);
		return "goods/goodsList";
	}
	
	@RequestMapping(value = "/goodsArticle", method = RequestMethod.GET)
	public String goodsArticle(Model model,@RequestParam("code") String code) {
		Goods goods = goodsService.getGoods(code);
		if (goods == null) {
			return "/";
		}
		int category = goodsService.getCategoryNumber(code);
		model.addAttribute("category_1",goodsService.getCategoryName(category/100*100));
		model.addAttribute("category_2",goodsService.getCategoryName(category));
		model.addAttribute("goods", goods);
		return "goods/goodsArticle";
	}
}
