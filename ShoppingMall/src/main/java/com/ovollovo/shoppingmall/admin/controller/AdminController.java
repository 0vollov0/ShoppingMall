package com.ovollovo.shoppingmall.admin.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.ovollovo.shoppingmall.service.AdminJson;
import com.ovollovo.shoppingmall.service.AdminService;
import com.ovollovo.shoppingmall.service.GoodsService;
import com.ovollovo.shoppingmall.service.OrderService;
import com.ovollovo.shoppingmall.util.UploadFileUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private AdminJson adminJson;

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

		return "admin/registerForm";
	}

	@RequestMapping(value = "/goodsModifyForm", method = RequestMethod.POST)
	public String goodsModifyForm(ModelMap model, @ModelAttribute("goods") Goods goods,
			@RequestParam("category_1") String category_1, @RequestParam("category_2") String category_2) {
		model.remove("resultData");
		model.addAttribute("goods", goods);
		model.addAttribute("category_1", goodsService.getCategoryCode(category_1));
		model.addAttribute("category_2", goodsService.getCategoryCode(category_2));
		return "admin/goodsModifyForm";
	}

	@RequestMapping(value = "/registerGoods", method = RequestMethod.POST)
	public String registerGoods(Model model, @ModelAttribute("goods") Goods goods, MultipartFile imageFile) {
		String imgUploadPath = uploadPath + File.separator + "resources/images/goodsImages";
		String categoryPath = UploadFileUtils.getCategoryPath(imgUploadPath, goods.getCategory());

		String fileName = null;

		if (imageFile != null) {
			try {
				fileName = UploadFileUtils.fileUpload(imgUploadPath, imageFile.getOriginalFilename(),
						imageFile.getBytes(), categoryPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}
		goods.setThumbnail_image(File.separator + "resources\\images\\goodsImages" + categoryPath + File.separator
				+ "thumbnail" + File.separator + "thumbnail_" + fileName);
		model.addAttribute("resultData", adminService.registerGoods(goods));

		return "admin/registerForm";
	}

	@RequestMapping(value = "/modifyGoods", method = RequestMethod.POST)
	public String modifyGoods(Model model, @ModelAttribute("goods") Goods goods,
			@RequestParam("wherecode") String wherecode, MultipartFile imageFile) {

		String imgUploadPath = uploadPath + File.separator + "resources/images/goodsImages";
		String categoryPath = UploadFileUtils.getCategoryPath(imgUploadPath, goods.getCategory());

		String fileName = null;

		if (imageFile != null && imageFile.getOriginalFilename().length() > 1) {
			try {
				fileName = UploadFileUtils.fileUpload(imgUploadPath, imageFile.getOriginalFilename(),
						imageFile.getBytes(), categoryPath);
				goodsService.modifyGoodsImage(wherecode, File.separator + "resources\\images\\goodsImages"
						+ categoryPath + File.separator + "thumbnail" + File.separator + "thumbnail_" + fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("resultData", goodsService.modifyGoods(goods, wherecode));

		return "admin/goodsModifyForm";
	}
	
	@RequestMapping(value = "/deleteGoods")
	public String deleteGoods(@RequestParam("code") String code) {
		goodsService.deleteGoods(code);
		return "index";
	}

	@RequestMapping(value = "adminOrderList")
	public String memberOrderList(Model model) {
		model.addAttribute("orderList", orderService.getOrderList());
		return "admin/adminOrderList";
	}

	@RequestMapping(value = "/registerShippingInfo", method = RequestMethod.POST)
	public @ResponseBody JsonObject registerShippingInfo(@RequestParam("code") int code,
			@RequestParam("companyCode") String companyCode, @RequestParam("invoiceNumber") String invoiceNumber) {
		orderService.registerShippingInfo(code, companyCode, invoiceNumber);
		return adminJson.getRegisterShippingInfoResultJson(code, companyCode, invoiceNumber);
	}
}
