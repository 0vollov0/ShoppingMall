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
			jsonObject.addProperty("message", "��ǰ��� ����");
			jsonObject.addProperty("code", code);
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "�ߺ��� �ڵ尡 �����մϴ�.");
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
			jsonObject.addProperty("message", "���������� ���� �Ǿ����ϴ�.");
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "�������� �ʴ� �ڵ��Դϴ�.");
			break;
		case 2:
			jsonObject.addProperty("result", 2);
			jsonObject.addProperty("message", "�ߺ��� �ڵ尡 �����մϴ�.");
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

