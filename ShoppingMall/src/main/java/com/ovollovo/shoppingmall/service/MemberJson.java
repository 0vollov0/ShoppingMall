package com.ovollovo.shoppingmall.service;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Repository
public class MemberJson {
	Gson gson = new Gson();
	
	public String getLoginResultJson(int key) {
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
		return gson.toJson(jsonObject);

	}
	
	public String getJoinResultJson(int key) {
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

		default:
			break;
		}
		return gson.toJson(jsonObject);
	}
}
