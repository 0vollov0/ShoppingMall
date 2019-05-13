package com.ovollovo.shoppingmall.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.member.dao.MemberMapper;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberMapper mapper;
	
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
		Member member = mapper.loginMember(id, pw);
		if (member != null) {
			System.out.println("login successed");
		}else {
			System.out.println("login failed");
		}
		return "index";
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
		mapper.joinMember(id, pw, email);
		return "index";
	}
}
