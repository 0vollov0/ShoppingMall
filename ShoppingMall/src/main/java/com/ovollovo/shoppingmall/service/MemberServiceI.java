package com.ovollovo.shoppingmall.service;

import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.goods.Goods;
import com.ovollovo.shoppingmall.member.Member;

public interface MemberServiceI {
	//public boolean joinMember(String id, String pw, String email);
	public JsonObject joinMember(Member member,String captchaKey,String userCaptchaKey) throws Exception;

	public JsonObject loginMember(String id, String pw,HttpSession session);
	
	public Member getMember(String id,String pw);
	
	public int updateAuthstatus(Member member);
	
	public JsonObject getCaptchaKeyNImage();
	
	public void deleteCaptchaImage(String image);
	
	public Goods[] getNewGoods();
	
}
