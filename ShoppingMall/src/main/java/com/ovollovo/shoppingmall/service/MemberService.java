package com.ovollovo.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.member.authentication.MailHandler;
import com.ovollovo.shoppingmall.member.authentication.TempKey;
import com.ovollovo.shoppingmall.member.dao.MemberMapper;

@Service
public class MemberService implements MemberServiceI {
	@Autowired
	private MemberMapper mapper;

	@Autowired
	private JavaMailSender mailSender;

	/*
	 * @Override
	 * 
	 * @Transactional public boolean joinMember(String id, String pw, String email)
	 * { if (duplicateIdCheck(id)) { return false; } if (duplicateEmailCheck(email))
	 * { return false; } mapper.joinMember(id, pw, email);
	 * 
	 * return true; }
	 */

	@Override
	@Transactional
	public int joinMember(Member member) throws Exception {
		if (duplicateIdCheck(member.getId())) {
			return 1;
		}
		if (duplicateEmailCheck(member.getEmail())) {
			return 2;
		}
		mapper.joinMember(member.getId(), member.getPw(), member.getEmail());

		String authkey = new TempKey().getKey(50, false);
		System.out.println(authkey);

		member.setAuthkey(authkey);
		System.out.println(member.getAuthkey());
		mapper.createAuthkey(member.getId(), member.getAuthkey());

		MailHandler sendMail = new MailHandler(mailSender);

		sendMail.setSubject("[Hoon's Board v2.0] 회원가입 이메일 인증");
		sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>").append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href='http://localhost:8090/shoppingmall/member/joinConfirm?id=").append(member.getId())
				.append("&email=").append(member.getEmail()).append("&authkey=").append(authkey)
				.append("' target='_blenk'>이메일 인증 확인</a>").toString());
		sendMail.setFrom("ovollov876@gmail.com ", "박신규");
		sendMail.setTo(member.getEmail());
		sendMail.send();

		return 0;
	}

	@Override
	public int loginMember(String id, String pw) {
		if (mapper.loginMember(id, pw) == null) {
			return 1;
		} else if (!getAuthstatus(id)) {
			return 2;
		}
		return 0;
	}

	private boolean getAuthstatus(String id) {
		return mapper.searchAuthstatus(id);
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

	@Override
	public Member getMember(String id, String pw) {
		return mapper.loginMember(id, pw);
	}

	@Override
	public int updateAuthstatus(Member member) {
		if (!member.getAuthkey().equals(mapper.searchAuthkey(member.getId()))) {
			return 1;
		}
		mapper.confirmAuthstatus(member.getId());
		return 0;
	}
}
