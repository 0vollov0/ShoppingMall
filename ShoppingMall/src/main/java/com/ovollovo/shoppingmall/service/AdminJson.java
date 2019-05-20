package com.ovollovo.shoppingmall.service;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Repository
public class AdminJson {
	Gson gson = new Gson();
	
	public JsonObject getRegisterGoodsResultJson(int key) {
		JsonObject jsonObject = new JsonObject();
		switch (key) {
		case 0:
			jsonObject.addProperty("result", 0);
			jsonObject.addProperty("message", "��ǰ��� ����");
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "�ߺ��� �ڵ尡 �����մϴ�.");
			break;
		case 2:
			jsonObject.addProperty("result", 2);
			jsonObject.addProperty("message", "--");
			break;
		case 3:
			jsonObject.addProperty("result", 3);
			jsonObject.addProperty("message", "ȸ������ ����");
			break;
		default:
			break;
		}
		return jsonObject;
	}
}
