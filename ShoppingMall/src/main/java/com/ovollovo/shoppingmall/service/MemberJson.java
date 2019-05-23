package com.ovollovo.shoppingmall.service;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Repository
public class MemberJson {
	Gson gson = new Gson();
	
	public JsonObject getLoginResultJson(int key) {
		JsonObject jsonObject = new JsonObject();
		switch (key) {
		case 0:
			jsonObject.addProperty("result", 0);
			jsonObject.addProperty("message", "�α��� ����");
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "���̵� �Ǵ� ��й�ȣ�� ���� �ʽ��ϴ�.");
			break;
		case 2:
			jsonObject.addProperty("result", 2);
			jsonObject.addProperty("message", "���� ������ �ʿ��� ȸ���Դϴ�.");
			break;

		default:
			break;
		}
		return jsonObject;

	}
	
	public JsonObject getJoinResultJson(int key) {
		JsonObject jsonObject = new JsonObject();
		switch (key) {
		case 0:
			jsonObject.addProperty("result", 0);
			jsonObject.addProperty("message", "ȸ������ ����");
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "�̹� �����ϴ� ���̵� �Դϴ�.");
			break;
		case 2:
			jsonObject.addProperty("result", 2);
			jsonObject.addProperty("message", "�̹� �����ϴ� Email �Դϴ�.");
			break;
		case 3:
			jsonObject.addProperty("result", 3);
			jsonObject.addProperty("message", "���� �̹����� ���ڿ� �Է� �� ���ڰ� �ٸ��ϴ�.");
			break;
		default:
			break;
		}
		return jsonObject;
	}
	
	public JsonObject getCaptchaJson(Map<String, String> captchaMap) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("key", captchaMap.get("key"));
		jsonObject.addProperty("image", captchaMap.get("image"));
		return jsonObject;
		
	}
	
	public JsonObject getCaptchaResultJson() {
		JsonObject jsonObject = new JsonObject();
		
		return jsonObject;
	}
	
	public JsonObject getShoppingBasketResultJson(int basketSize) {
		JsonObject jsonObject = new JsonObject();
		//jsonObject.addProperty("basketLength", basketSize);
		jsonObject.addProperty("message", "��ٱ��Ͽ� "+basketSize+"���� ��ǰ�� �ֽ��ϴ�.");
		return jsonObject;
	}
	
	public JsonObject getDeleteShoppingBasketResultJson(String code) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("result", code);
		return jsonObject;
	}
}
