package com.ovollovo.shoppingmall.service;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.goods.Category;

@Repository
public class OrderJson {
	Gson gson = new Gson();

	public JsonObject getOrderResultJson(int key) {
		JsonObject jsonObject = new JsonObject();
		switch (key) {
		case 0:
			jsonObject.addProperty("result", 0);
			jsonObject.addProperty("message", "���� ����");
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "������ �׸��� ����ֽ��ϴ�.");
			break;
		case 2:
			jsonObject.addProperty("result", 2);
			jsonObject.addProperty("message", "������ �׸��� ����ֽ��ϴ�.");
			break;
		case 3:
			jsonObject.addProperty("result", 3);
			jsonObject.addProperty("message", "�����ȣ �׸��� ����ֽ��ϴ�.");
			break;
		case 4:
			jsonObject.addProperty("result", 4);
			jsonObject.addProperty("message", "�ּ� �׸��� ����ֽ��ϴ�.");
			break;
		case 5:
			jsonObject.addProperty("result", 5);
			jsonObject.addProperty("message", "���ּ� �׸��� ����ֽ��ϴ�.");
			break;
		case 6:
			jsonObject.addProperty("result", 6);
			jsonObject.addProperty("message", "�߸��� �����Դϴ�..");
			break;
		default:
			break;
		}
		return jsonObject;
	}
}
