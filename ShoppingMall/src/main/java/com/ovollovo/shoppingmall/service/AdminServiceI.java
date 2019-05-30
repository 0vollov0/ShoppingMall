package com.ovollovo.shoppingmall.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.goods.Goods;

public interface AdminServiceI {
	public boolean checkAdmin(HttpSession session);
	
	public JsonObject registerGoods(Goods goods,MultipartFile imageFile);
	
	public void modifyGoods(Goods goods,String wherecode, MultipartFile imageFile);
	
	public JsonObject modifyGoodsResult(String code,String wherecode);
	
	public void deleteGoods(String code);
	
}
