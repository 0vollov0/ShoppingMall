package com.ovollovo.shoppingmall.service;

import com.ovollovo.shoppingmall.member.Member;

public interface MemberServiceI {
	//public boolean joinMember(String id, String pw, String email);
	public int joinMember(Member member) throws Exception;

	public int loginMember(String id, String pw);
	
	public Member getMember(String id,String pw);
	
	public int updateAuthstatus(Member member);
}
