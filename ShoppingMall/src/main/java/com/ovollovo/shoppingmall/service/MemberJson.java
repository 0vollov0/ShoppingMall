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
			jsonObject.addProperty("message", "로그인 성공");
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "아이디 또는 비밀번호가 맞지 않습니다.");
			break;
		case 2:
			jsonObject.addProperty("result", 2);
			jsonObject.addProperty("message", "메일 인증이 필요한 회원입니다.");
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
			jsonObject.addProperty("message", "회원가입 성공");
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "이미 존재하는 아이디 입니다.");
			break;
		case 2:
			jsonObject.addProperty("result", 2);
			jsonObject.addProperty("message", "이미 존재하는 Email 입니다.");
			break;
		case 3:
			jsonObject.addProperty("result", 3);
			jsonObject.addProperty("message", "보안 이미지의 문자와 입력 한 문자가 다릅니다.");
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
		jsonObject.addProperty("message", "장바구니에 "+basketSize+"개의 상품이 있습니다.");
		return jsonObject;
	}
	
	public JsonObject getDeleteShoppingBasketResultJson(String code) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("result", code);
		return jsonObject;
	}
}
