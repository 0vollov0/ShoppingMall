package com.ovollovo.shoppingmall.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.member.authentication.MailHandler;
import com.ovollovo.shoppingmall.member.authentication.TempKey;
import com.ovollovo.shoppingmall.member.dao.MemberMapper;
import com.ovollovo.shoppingmall.openapi.NaverCaptchaAPI;

@Service
public class MemberService implements MemberServiceI {
	@Autowired
	private MemberMapper memberMmapper;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MemberJson memberJson;

	@Autowired
	private NaverCaptchaAPI captcha;

	@Override
	@Transactional
	public JsonObject joinMember(Member member, String captchaKey, String userCaptchaKey) throws Exception {
		if (duplicateCheckIdCheck(member.getId())) {
			return memberJson.getJoinResultJson(1);
		}
		if (duplicateCheckEmailCheck(member.getEmail())) {
			return memberJson.getJoinResultJson(2);
		}
		if (!checkCaptchaKey(captchaKey, userCaptchaKey)) {
			return memberJson.getJoinResultJson(3);
		}
		memberMmapper.joinMember(member.getId(), member.getPw(), member.getEmail());

		String authkey = new TempKey().getKey(50, false);
		System.out.println(authkey);

		member.setAuthkey(authkey);
		System.out.println(member.getAuthkey());
		memberMmapper.createAuthkey(member.getId(), member.getAuthkey());

		MailHandler sendMail = new MailHandler(mailSender);

		sendMail.setSubject("SHOPPING MALL ȸ������ ����");
		sendMail.setText(new StringBuffer().append("<h1>SHOPPING MALL EMAIL ����</h1>")
				.append("<p>�Ʒ� ��ũ�� Ŭ���Ͻø� �̸��� ������ �Ϸ�˴ϴ�.</p>")
				.append("<a href='http://localhost:8090/shoppingmall/member/joinConfirm?id=").append(member.getId())
				.append("&email=").append(member.getEmail()).append("&authkey=").append(authkey)
				.append("' target='_blenk'>�̸��� ���� Ȯ��</a>").toString());
		sendMail.setFrom("ovollov876@gmail.com ", "�ڽű�");
		sendMail.setTo(member.getEmail());
		sendMail.send();

		return memberJson.getJoinResultJson(0);
	}

	@Override
	public JsonObject loginMember(String id, String pw, HttpSession session) {
		if (memberMmapper.loginMember(id, pw) == null) {
			// return 1;
			return memberJson.getLoginResultJson(1);
		} else if (!getAuthstatus(id)) {
			// return 2;
			return memberJson.getLoginResultJson(2);
		}
		session.setAttribute("member", getMember(id, pw));
		session.setMaxInactiveInterval(3600);
		return memberJson.getLoginResultJson(0);
	}

	private boolean getAuthstatus(String id) {
		return memberMmapper.searchAuthstatus(id);
	}

	private boolean duplicateCheckIdCheck(String id) {
		if (memberMmapper.searchMember(id) != null) {
			return true;
		}
		return false;
	}

	private boolean duplicateCheckEmailCheck(String email) {
		if (memberMmapper.searchMember(email) != null) {
			return true;
		}
		return false;
	}

	private boolean checkCaptchaKey(String captchaKey, String userCaptchaKey) {
		Gson gson = new Gson();
		JsonObject jsonObject = captcha.compareCaptchaKey(captchaKey, userCaptchaKey);
		String rsult = gson.toJson(jsonObject);
		rsult = rsult.substring(10, 14); // result value

		if (rsult.equals("true")) {
			return true;
		}

		return false;
	}

	@Override
	public Member getMember(String id, String pw) {
		return memberMmapper.loginMember(id, pw);
	}

	@Override
	public int updateAuthstatus(Member member) {
		if (!member.getAuthkey().equals(memberMmapper.searchAuthkey(member.getId()))) {
			return 1;
		}
		memberMmapper.confirmAuthstatus(member.getId());
		memberMmapper.deleteAuthkey(member.getId());
		return 0;
	}

	@Override
	public JsonObject getCaptchaKeyNImage() {
		Map<String, String> captchaMap = new HashMap<String, String>();
		String keyValue = captcha.getCaptchaKey();
		String imageValue = captcha.getCaptchaImage(keyValue);
		captchaMap.put("key", keyValue);
		captchaMap.put("image", imageValue);
		return memberJson.getCaptchaJson(captchaMap);
	}

	@Override
	public void deleteCaptchaImage(String image) {
		if (image != null) {
			File file = new File(
					"C:\\Users\\OvollovO\\Documents\\GitHub\\ShoppingMall\\ShoppingMall\\src\\main\\webapp\\resources\\images\\"
							+ image);
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getPath());
			while (!file.delete()) {
				System.out.println("��������");

			}
			System.out.println("����");
		}
	}

	@Override
	public JsonObject getShoppingBasketResultJson(String goodsName) {
		return memberJson.getShoppingBasketResultJson(goodsName);
	}

	@Override
	public JsonObject getDeleteShoppingBasketResultJson(String code) {
		return memberJson.getDeleteShoppingBasketResultJson(code);
	}

	@Override
	public JsonObject modifyMember(String id, String pw, String newpw) {
		Member member = memberMmapper.loginMember(id, pw);
		if (member == null) {
			return memberJson.getModifyResultJson(1);
		}
		memberMmapper.modifyMember(id, pw, newpw);
		return memberJson.getModifyResultJson(0);
	}

	@Override
	public void deleteMember(String id) {
		memberMmapper.deleteMember(id);
	}

}
