package com.ovollovo.shoppingmall.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.goods.Goods;
import com.ovollovo.shoppingmall.service.AdminService;
import com.ovollovo.shoppingmall.service.GoodsService;
import com.ovollovo.shoppingmall.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private GoodsService goodsService;

	@Resource(name = "uploadPath")
	private String uploadPath;

	@ModelAttribute("contextPath")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	@RequestMapping(value = "/registerForm")
	public String registerForm(ModelMap model) {
		model.remove("resultData");

		return "admin/goodsRegisterForm";
	}

	@RequestMapping(value = "/goodsModifyForm", method = RequestMethod.POST)
	public String goodsModifyForm(ModelMap model, @ModelAttribute("goods") Goods goods,@RequestParam("category_1") String category_1, @RequestParam("category_2") String category_2) {
		model.remove("resultData");
		model.addAttribute("goods", goods);
		model.addAttribute("category_1", goodsService.getCategoryCode(category_1));
		model.addAttribute("category_2", goodsService.getCategoryCode(category_2));
		return "admin/goodsModifyForm";
	}

	@RequestMapping(value = "/registerGoods", method = RequestMethod.POST)
	public String registerGoods(Model model, @ModelAttribute("goods") Goods goods, MultipartFile imageFile) {
		model.addAttribute("resultData", adminService.registerGoods(goods,imageFile));

		return "admin/goodsRegisterForm";
	}
	@RequestMapping(value = "/modifyGoods", method = RequestMethod.POST)
	public String modifyGoods(Model model, @ModelAttribute("goods") Goods goods,@RequestParam("wherecode") String wherecode, MultipartFile imageFile) {
		adminService.modifyGoods(goods, wherecode, imageFile);
		model.addAttribute("message", "상품 수정 성공");
		return "admin/goodsModifyForm";
	}
	@RequestMapping(value = "/getModifyGoodsFormResult", method = RequestMethod.POST)
	public @ResponseBody JsonObject getModifyGoodsFormResult(@RequestParam("code") String code,@RequestParam("wherecode") String wherecode) {
		return adminService.modifyGoodsResult(code, wherecode);
	};
	
	@RequestMapping(value = "/deleteGoods")
	public String deleteGoods(@RequestParam("code") String code) {
		adminService.deleteGoods(code);
		return "redirect:/";
	}

	@RequestMapping(value = "adminOrderList")
	public String memberOrderList(Model model,@RequestParam("page") int page) {
		if (page <= 0) {
			page = 1;
		}
		model.addAttribute("orderList", orderService.getOrderList(page));
		int maxPage = orderService.getCurrentMaxPage(page,null);
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
		return "admin/adminOrderList";
	}
}
