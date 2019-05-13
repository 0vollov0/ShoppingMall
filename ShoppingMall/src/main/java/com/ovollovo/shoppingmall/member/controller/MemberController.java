package com.ovollovo.shoppingmall.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.member.dao.MemberMapper;
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

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		Member member = memberService.loginMember(id, pw);
		if (member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			session.setMaxInactiveInterval(3600);
			return "index";
		}
		return "member/loginForm";
	}

	@RequestMapping(value = "/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}

	@RequestMapping(value = "/join" ,method = RequestMethod.POST)
	public String join(HttpServletRequest request,Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		if (memberService.joinMember(id, pw, email)) {
			System.out.println("회원가입 성공");
		}else {
			System.out.println("ID 또는 Email 중복");
		}
		return "index";
	}
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
}
