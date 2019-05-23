package com.ovollovo.shoppingmall.service;

import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.goods.Category_1;
import com.ovollovo.shoppingmall.goods.Category_2;
import com.ovollovo.shoppingmall.goods.Goods;

public interface AdminServiceI {
	public boolean checkAdmin(HttpSession session);
	
	public JsonObject registerGoods(Goods goods);
	
}
