package com.ovollovo.shoppingmall.service;

import org.springframework.stereotype.Repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.goods.Category;

@Repository
public class GoodsJson {
	public JsonObject getModifyGoodsResultJson(int key,String code) {
		JsonObject jsonObject = new JsonObject();
		switch (key) {
		case 0:
			jsonObject.addProperty("result", 0);
			jsonObject.addProperty("message", "성공적으로 수정 되었습니다.");
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "존재하지 않는 코드입니다.");
			break;
		case 2:
			jsonObject.addProperty("result", 2);
			jsonObject.addProperty("message", "중복된 코드가 존재합니다.");
			break;
		default:
			break;
		}
		return jsonObject;
	}
	
	public JsonArray getCategoryJson(Category[] category) {
		JsonArray jsonArray = new JsonArray();
		for (Category item : category) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("code", item.getCode());
			jsonObject.addProperty("name", item.getName());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
}
