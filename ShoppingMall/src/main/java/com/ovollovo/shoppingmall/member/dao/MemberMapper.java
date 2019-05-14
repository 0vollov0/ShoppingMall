package com.ovollovo.shoppingmall.member.dao;

import org.apache.ibatis.annotations.Param;

import com.ovollovo.shoppingmall.member.Member;

public interface MemberMapper {
	public void joinMember(@Param("id") String id, @Param("pw") String pw, @Param("email") String email);

	public Member loginMember(@Param("id") String id, @Param("pw") String pw);
	
	public String searchMember(@Param("id") String id);
	
	public String searchEmail(@Param("email") String email);
	
	public void createAuthkey(@Param("id") String id,@Param("authkey") String authkey);	
	
	public void confirmAuthstatus(@Param("id") String id); 
	
	public boolean searchAuthstatus(@Param("id") String id);
	
	public String searchAuthkey(@Param("id") String id); 
}
