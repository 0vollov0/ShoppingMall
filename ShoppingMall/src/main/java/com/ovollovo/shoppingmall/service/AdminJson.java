package com.ovollovo.shoppingmall.service;

import org.springframework.stereotype.Repository;

import com.google.gson.JsonObject;

@Repository
public class AdminJson {
	public JsonObject getRegisterGoodsResultJson(int key,String code) {
		JsonObject jsonObject = new JsonObject();
		switch (key) {
		case 0:
			jsonObject.addProperty("result", 0);
			jsonObject.addProperty("message", "제품등록 성공");
			jsonObject.addProperty("code", code);
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "중복된 코드가 존재합니다.");
			break;
		default:
			break;
		}
		return jsonObject;
	}
	
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
	
	public JsonObject getRegisterShippingInfoResultJson(int code,String companyCode,String invoiceNumber) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", code);
		jsonObject.addProperty("companyCode", companyCode);
		jsonObject.addProperty("invoiceNumber", invoiceNumber);
		return jsonObject;
	}
}

