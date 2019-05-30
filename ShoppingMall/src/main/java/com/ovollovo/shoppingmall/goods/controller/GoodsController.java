package com.ovollovo.shoppingmall.goods.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
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
	public String goodsList(Model model,@RequestParam("category") int category,@RequestParam("page") int page) {
		if (page <= 0) {
			page = 1;
		}
		int maxPage = goodsService.getCurrentMaxPage(category,page);
		int minPage;
		if (maxPage%10 > 0) {
			minPage = maxPage/10+1;
		}else {
			minPage = (maxPage-1)/10+1;
		}
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("minPage", minPage);
		if (page > maxPage) {
			model.addAttribute("currentPage", maxPage);
		}else {
			model.addAttribute("currentPage", page);
		}
		if (category % 100 != 0) {
			model.addAttribute("category_2",goodsService.getCategoryName(category));
		}
		model.addAttribute("category_1",goodsService.getCategoryName(category/100*100));
		model.addAttribute("goodsList", goodsService.getGoodsList(category, page));
		model.addAttribute("category",category);
		return "goods/goodsList";
	}
	
	@RequestMapping(value = "/searchGoods", method = RequestMethod.GET)
	public String searchGoods(Model model,@RequestParam("name") String name,@RequestParam("page") int page) {
		if (page <= 0) {
			page = 1;
		}
		int maxPage = goodsService.getCurrentMaxPage(name,page);
		int minPage;
		if (maxPage%10 > 0) {
			minPage = maxPage/10+1;
		}else {
			minPage = (maxPage-1)/10+1;
		}
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("minPage", minPage);
		if (page > maxPage) {
			model.addAttribute("currentPage", maxPage);
		}else {
			model.addAttribute("currentPage", page);
		}
		model.addAttribute("category_1","검색 결과 :");
		model.addAttribute("category_2",name);
		model.addAttribute("goodsList", goodsService.getGoodsSearchResult(name,page));
		model.addAttribute("search", "searchGoods");
		return "goods/goodsList";
	}
	
	@RequestMapping(value = "/goodsArticle", method = RequestMethod.GET)
	public String goodsArticle(Model model,@RequestParam("code") String code) {
		Goods goods = goodsService.getGoods(code);
		if (goods == null) {
			return "redirect:/";
		}
		int category = goodsService.getCategoryNumber(code);
		model.addAttribute("category_1",goodsService.getCategoryName(category/100*100));
		model.addAttribute("category_2",goodsService.getCategoryName(category));
		model.addAttribute("goods", goods);
		return "goods/goodsArticle";
	}
	
	@RequestMapping(value = "/getCategory", method = RequestMethod.GET)
	public @ResponseBody JsonArray getCategory() {
		return goodsService.getCategory();
	}
}
