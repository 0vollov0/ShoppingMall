package com.ovollovo.shoppingmall.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@ModelAttribute("contextPath")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public void test(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session,
			HttpServletResponse response) {
		String resultJson = memberService.loginMember(id, pw,session);
		try {
			response.getWriter().write(resultJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}

	
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public void test(@ModelAttribute("member") Member member,HttpServletResponse response) {
		System.out.println(member);
		try {
			String resultJson = memberService.joinMember(member);
			response.getWriter().write(resultJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "joinConfirm", method = RequestMethod.GET)
	public String joinConfirm(@ModelAttribute("member") Member member) {
		switch (memberService.updateAuthstatus(member)) {
		case 0:
			System.out.println("메일인증 성공");
			break;
		case 1:
			System.out.println("메일인증 실패");
			break;
		default:
			break;
		}

		return "redirect:/";
	}
	
	private String getLoginResultJson(int key) {
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		switch (key) {
		case 0:
			jsonObject.addProperty("result", 0);
			jsonObject.addProperty("message", "로그인 성공");
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "아이디 또는 비밀번호가 맞지 않습니다.");
			break;
		case 2:
			jsonObject.addProperty("result", 2);
			jsonObject.addProperty("message", "메일 인증이 필요한 회원입니다.");
			break;

		default:
			break;
		}
		return gson.toJson(jsonObject);

	}
	
	private String getJoinResultJson(int key) {
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		switch (key) {
		case 0:
			jsonObject.addProperty("result", 0);
			jsonObject.addProperty("message", "회원가입 성공");
			break;
		case 1:
			jsonObject.addProperty("result", 1);
			jsonObject.addProperty("message", "이미 존재하는 아이디 입니다.");
			break;
		case 2:
			jsonObject.addProperty("result", 2);
			jsonObject.addProperty("message", "이미 존재하는 Email 입니다.");
			break;

		default:
			break;
		}
		return gson.toJson(jsonObject);
	}
}
