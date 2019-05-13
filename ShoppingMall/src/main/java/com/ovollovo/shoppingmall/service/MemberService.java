package com.ovollovo.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.member.dao.MemberMapper;

@Service
public class MemberService implements MemberServiceI {
	@Autowired
	private MemberMapper mapper;

	@Override
	public boolean joinMember(String id, String pw, String email) {
		if (duplicateIdCheck(id)) {
			return false;
		}
		if (duplicateEmailCheck(email)) {
			return false;
		}
		mapper.joinMember(id, pw, email);
		return true;
	}

	@Override
	public Member loginMember(String id, String pw) {
		return mapper.loginMember(id, pw);
	}
	
	private boolean duplicateIdCheck(String id) {
		if (mapper.searchMember(id) != null) {
			return true;
		}
		return false;
	}
	
	private boolean duplicateEmailCheck(String email) {
		if (mapper.searchMember(email) != null) {
			return true;
		}
		return false;
	}
}
