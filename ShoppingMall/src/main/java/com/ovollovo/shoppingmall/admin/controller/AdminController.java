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
import com.ovollovo.shoppingmall.service.OrderService;
import com.ovollovo.shoppingmall.util.UploadFileUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AdminJson adminJson;

	@Resource(name = "uploadPath")
	private String uploadPath;

	@ModelAttribute("contextPath")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	@RequestMapping(value = "/registerForm")
	public String registerForm(HttpSession session,ModelMap model) {
		model.remove("resultData");
		
		return "admin/registerForm";
	}

	@RequestMapping(value = "/registerGoods", method = RequestMethod.POST)
	public String registerGoods(HttpSession session,Model model, @ModelAttribute("goods") Goods goods, MultipartFile imageFile) {
		System.out.println(goods.getDescription());
		String imgUploadPath = uploadPath + File.separator + "resources/images/goodsImages";
		String categoryPath = UploadFileUtils.getCategoryPath(imgUploadPath, goods.getCategory());

		String fileName = null;

		if (imageFile != null) {
			try {
				fileName = UploadFileUtils.fileUpload(imgUploadPath, imageFile.getOriginalFilename(), imageFile.getBytes(), categoryPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}
		
		//goods.setThumbnail_image(File.separator + "resources\\images\\goodsImages" + categoryPath + File.separator + "thunmnail" + File.separator + "s_" + fileName);
		goods.setThumbnail_image(File.separator + "resources\\images\\goodsImages" + categoryPath + File.separator + "thumbnail" + File.separator + "thumbnail_" + fileName);
		model.addAttribute("resultData", adminService.registerGoods(goods));
		
		return "admin/registerForm";
	}
	
	@RequestMapping(value = "adminOrderList")
	public String memberOrderList(Model model) {
		model.addAttribute("orderList", orderService.getOrderList());
		return "admin/adminOrderList";
	}
	
	@RequestMapping(value = "/registerShippingInfo", method = RequestMethod.POST)
	public @ResponseBody JsonObject registerShippingInfo(@RequestParam("code") int code,@RequestParam("companyCode") String companyCode,@RequestParam("invoiceNumber") String invoiceNumber) {
		orderService.registerShippingInfo(code, companyCode, invoiceNumber);
		return adminJson.getRegisterShippingInfoResultJson(code,companyCode, invoiceNumber);
	}
}
