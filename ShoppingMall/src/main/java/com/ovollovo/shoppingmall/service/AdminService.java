package com.ovollovo.shoppingmall.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.goods.Goods;
import com.ovollovo.shoppingmall.goods.dao.GoodsMapper;
import com.ovollovo.shoppingmall.member.Member;

@Service
public class AdminService implements AdminServiceI {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private AdminJson adminJson;

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
	public JsonObject registerGoods(Goods goods) {
		if (goodsMapper.searchCode(goods.getCode()) != null) {
			return adminJson.getRegisterGoodsResultJson(1);
		}
		goodsMapper.registerGoods(goods.getName(), goods.getCode(), goods.getCategory(),goods.getPrice(), goods.getStock(), goods.getDescription(), goods.getThumbnail_image());
		
		return adminJson.getRegisterGoodsResultJson(0);
	}

}
