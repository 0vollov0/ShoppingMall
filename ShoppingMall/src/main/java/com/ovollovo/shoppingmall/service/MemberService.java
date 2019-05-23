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
import com.ovollovo.shoppingmall.goods.Goods;
import com.ovollovo.shoppingmall.goods.dao.GoodsMapper;
import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.member.authentication.MailHandler;
import com.ovollovo.shoppingmall.member.authentication.TempKey;
import com.ovollovo.shoppingmall.member.dao.MemberMapper;
import com.ovollovo.shoppingmall.openapi.NaverCaptchaAPI;

@Service
public class MemberService implements MemberServiceI {
	@Autowired
	private MemberMapper mapper;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MemberJson memberJson;

	@Autowired
	private NaverCaptchaAPI captcha;
	
	@Autowired
	private GoodsMapper goodsMapper;

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
	public JsonObject joinMember(Member member,String captchaKey,String userCaptchaKey) throws Exception {
		if (duplicateCheckIdCheck(member.getId())) {
			// return 1;
			return memberJson.getJoinResultJson(1);
		}
		if (duplicateCheckEmailCheck(member.getEmail())) {
			// return 2;
			return memberJson.getJoinResultJson(2);
		}
		if (!checkCaptchaKey(captchaKey,userCaptchaKey)) {
			return memberJson.getJoinResultJson(3);
		}
		mapper.joinMember(member.getId(), member.getPw(), member.getEmail());

		String authkey = new TempKey().getKey(50, false);
		System.out.println(authkey);

		member.setAuthkey(authkey);
		System.out.println(member.getAuthkey());
		mapper.createAuthkey(member.getId(), member.getAuthkey());

		MailHandler sendMail = new MailHandler(mailSender);

		sendMail.setSubject("SHOPPING MALL 회원가입 인증");
		sendMail.setText(new StringBuffer().append("<h1>SHOPPING MALL EMAIL 인증</h1>")
				.append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
				.append("<a href='http://localhost:8090/shoppingmall/member/joinConfirm?id=").append(member.getId())
				.append("&email=").append(member.getEmail()).append("&authkey=").append(authkey)
				.append("' target='_blenk'>이메일 인증 확인</a>").toString());
		sendMail.setFrom("ovollov876@gmail.com ", "박신규");
		sendMail.setTo(member.getEmail());
		sendMail.send();

		return memberJson.getJoinResultJson(0);
	}

	@Override
	public JsonObject loginMember(String id, String pw, HttpSession session) {
		if (mapper.loginMember(id, pw) == null) {
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
		return mapper.searchAuthstatus(id);
	}

	private boolean duplicateCheckIdCheck(String id) {
		if (mapper.searchMember(id) != null) {
			return true;
		}
		return false;
	}

	private boolean duplicateCheckEmailCheck(String email) {
		if (mapper.searchMember(email) != null) {
			return true;
		}
		return false;
	}
	
	private boolean checkCaptchaKey(String captchaKey,String userCaptchaKey) {
		Gson gson = new Gson();
		JsonObject jsonObject = captcha.compareCaptchaKey(captchaKey,userCaptchaKey);
		String rsult = gson.toJson(jsonObject);
		rsult = rsult.substring(10, 14); // result value
		
		if (rsult.equals("true")) {
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
		mapper.deleteAuthkey(member.getId());
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
		if (image!=null) {
			File file = new File("C:\\Users\\OvollovO\\Documents\\GitHub\\ShoppingMall\\ShoppingMall\\src\\main\\webapp\\resources\\images\\"+image);
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getPath());
			while (!file.delete()) {
				System.out.println("삭제삭제");
				
			}System.out.println("성공");
		}
	}

	@Override
	public Goods[] getNewGoods() {
		return goodsMapper.searchNewGoods();
	}

	@Override
	public JsonObject getShoppingBasketResultJson(int basketSize) {
		return memberJson.getShoppingBasketResultJson(basketSize);
	}

	@Override
	public JsonObject getDeleteShoppingBasketResultJson(String code) {
		return memberJson.getDeleteShoppingBasketResultJson(code);
	}

}
