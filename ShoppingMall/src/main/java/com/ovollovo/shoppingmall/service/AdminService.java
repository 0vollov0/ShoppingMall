package com.ovollovo.shoppingmall.service;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.goods.Goods;
import com.ovollovo.shoppingmall.goods.dao.GoodsMapper;
import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.util.UploadFileUtils;

@Service
public class AdminService implements AdminServiceI {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private AdminJson adminJson;
	
	@Resource(name = "uploadPath")
	private String uploadPath;

	@Override
	public boolean checkAdmin(HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		if (member == null) {
			return false;
		} else if (member.isAdmin() == false) {
			return false;
		}
		return true;
	}
	
	@Override
	public JsonObject registerGoods(Goods goods,MultipartFile imageFile) {
		if (goodsMapper.searchCode(goods.getCode()) != null) {
			return adminJson.getRegisterGoodsResultJson(1,null);
		}
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
		
		goods.setThumbnail_image(File.separator + "resources\\images\\goodsImages" + categoryPath + File.separator+ fileName);
		
		goodsMapper.registerGoods(goods.getName(), goods.getCode(), goods.getCategory(),goods.getPrice(), goods.getStock(), goods.getDescription(), goods.getThumbnail_image());
		return adminJson.getRegisterGoodsResultJson(0,goods.getCode());
	}
	
	@Override
	public void modifyGoods(Goods goods, String wherecode, MultipartFile imageFile) {
		goodsMapper.modifyGoods(goods.getName(), goods.getCode(), goods.getCategory(), goods.getPrice(),goods.getStock(), goods.getDescription(), wherecode);
		String imgUploadPath = uploadPath + File.separator + "resources/images/goodsImages";
		String categoryPath = UploadFileUtils.getCategoryPath(imgUploadPath, goods.getCategory());

		String fileName = null;

		if (imageFile != null && imageFile.getOriginalFilename().length() > 1) {
			try {
				fileName = UploadFileUtils.fileUpload(imgUploadPath, imageFile.getOriginalFilename(),imageFile.getBytes(), categoryPath);
				goodsMapper.modifyGoodsImage(wherecode, File.separator + "resources\\images\\goodsImages" + categoryPath + File.separator + fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public JsonObject modifyGoodsResult(String code,String wherecode) {
		if (goodsMapper.searchGoods(wherecode) == null) {
			return adminJson.getModifyGoodsResultJson(1, wherecode);
		}
		if (!code.equals(wherecode)) {
			if (goodsMapper.searchGoods(code) != null) {
				return adminJson.getModifyGoodsResultJson(2, wherecode);
			}
		}
		return adminJson.getModifyGoodsResultJson(0, wherecode);
	}

	@Override
	public void deleteGoods(String code) {
		goodsMapper.deleteGoods(code);
	}

}
