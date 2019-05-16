package com.ovollovo.shoppingmall.service;

import javax.servlet.http.HttpSession;

import com.ovollovo.shoppingmall.member.Member;

public interface MemberServiceI {
	//public boolean joinMember(String id, String pw, String email);
	public String joinMember(Member member) throws Exception;

	public String loginMember(String id, String pw,HttpSession session);
	
	public Member getMember(String id,String pw);
	
	public int updateAuthstatus(Member member);
}
