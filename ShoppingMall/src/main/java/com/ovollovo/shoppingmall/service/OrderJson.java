package com.ovollovo.shoppingmall.service;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "������ �׸��� ����ֽ��ϴ�.");
			break;
		case 3:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "�����ȣ �׸��� ����ֽ��ϴ�.");
			break;
		case 4:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "�ּ� �׸��� ����ֽ��ϴ�.");
			break;
		case 5:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "���ּ� �׸��� ����ֽ��ϴ�.");
			break;
		case 6:
			jsonObject.addProperty("result", 6);
			jsonObject.addProperty("message", "��ٱ��ϰ� ��� �ֽ��ϴ�.");
			break;
		case 7:
			jsonObject.addProperty("result", 7);
			jsonObject.addProperty("message", "�߸��� �����Դϴ�..");
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
	
	public JsonObject getDeliveryInfoResult(String[] deliveryInfo) {
		System.out.println(deliveryInfo);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("buyer", deliveryInfo[0]);
		jsonObject.addProperty("shippingRecipient", deliveryInfo[1]);
		jsonObject.addProperty("zipCode", deliveryInfo[2]);
		jsonObject.addProperty("address", deliveryInfo[3]);
		jsonObject.addProperty("detailAddress", deliveryInfo[4]);
		jsonObject.addProperty("reference", deliveryInfo[5]);
		return jsonObject;
	}
}
