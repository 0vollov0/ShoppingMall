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
import org.springframework.web.multipart.MultipartFile;

import com.ovollovo.shoppingmall.goods.Goods;
import com.ovollovo.shoppingmall.service.AdminService;
import com.ovollovo.shoppingmall.util.UploadFileUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Resource(name = "uploadPath")
	private String uploadPath;

	@ModelAttribute("contextPath")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	@RequestMapping(value = "/registerForm")
	public String registerForm(HttpSession session,ModelMap model) {
		/*
		if (adminService.checkAdmin(session) == false) {
			return "redirect:/";
		}*/
		model.remove("resultData");
		
		return "admin/registerForm";
	}

	/*
	@RequestMapping(value = "/registerGoods", method = RequestMethod.POST)
	public @ResponseBody JsonObject registerGoods(@ModelAttribute("goods") Goods goods, MultipartFile uploadfile) {
		if (uploadfile == null) {
			System.out.println("sex");
		}
		// System.out.println("originalfilename"+uploadfile.getOriginalFilename());
		// System.out.println("filename"+uploadfile.getName());
		return adminService.registerGoods(goods);
	}
	*/
	@RequestMapping(value = "/registerGoods", method = RequestMethod.POST)
	public String registerGoods(HttpSession session,Model model, @ModelAttribute("goods") Goods goods, MultipartFile imageFile) {
		/*
		if (adminService.checkAdmin(session) == false) {
			return "redirect:/";
		}*/
		System.out.println(goods.getDescription());
		String imgUploadPath = uploadPath + File.separator + "resources/images/goodsImages";
		//String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
		String classificationPath = UploadFileUtils.getClassificationPath(imgUploadPath, goods.getClassification_1(), goods.getClassification_2());

		String fileName = null;

		if (imageFile != null) {
			try {
				fileName = UploadFileUtils.fileUpload(imgUploadPath, imageFile.getOriginalFilename(), imageFile.getBytes(), classificationPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
		}
		
		//goods.setThumbnail_image(File.separator + "resources\\images\\goodsImages" + classificationPath + File.separator + "thunmnail" + File.separator + "s_" + fileName);
		goods.setThumbnail_image(File.separator + "resources\\images\\goodsImages" + classificationPath + File.separator + "thumbnail" + File.separator + "thumbnail_" + fileName);
		model.addAttribute("resultData", adminService.registerGoods(goods));
		
		return "admin/registerForm";
	}
}
