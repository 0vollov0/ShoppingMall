package com.ovollovo.shoppingmall.service;

import com.ovollovo.shoppingmall.member.Member;

public interface MemberServiceI {
	public boolean joinMember(String id, String pw, String email);

	public Member loginMember(String id, String pw);
}
