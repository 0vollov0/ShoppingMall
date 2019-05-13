package com.ovollovo.shoppingmall.member.dao;

import org.apache.ibatis.annotations.Param;

import com.ovollovo.shoppingmall.member.Member;

public interface MemberMapper {
	public void joinMember(@Param("id") String id, @Param("pw") String pw, @Param("email") String email);

	public Member loginMember(@Param("id") String id, @Param("pw") String pw);
}
