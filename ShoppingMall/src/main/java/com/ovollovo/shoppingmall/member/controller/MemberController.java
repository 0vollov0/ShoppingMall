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
		switch (memberService.loginMember(id, pw)) {
		case 0:
			HttpSession session = request.getSession();
			session.setAttribute("member", memberService.getMember(id, pw));
			session.setMaxInactiveInterval(3600);
			return "index";
		case 1:
			System.out.println("아이디 또는 비밀번호가 맞지 않습니다.");
			break;
		case 2:
			System.out.println("메일 인증이 필요한 회원입니다.");
			break;
		default:
			break;
		} 
		return "member/loginForm";
	}

	@RequestMapping(value = "/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}
	/*
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
	*/
	@RequestMapping(value = "/join" ,method = RequestMethod.POST)
	public String join(@ModelAttribute("member") Member member,Model model) {
		try {
			switch (memberService.joinMember(member)) {
			case 0:
				System.out.println("회원가입 성공");
				break;
			case 1:
				System.out.println("ID 중복");
				break;
			case 2:
				System.out.println("Email 중복");
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="joinConfirm", method=RequestMethod.GET)
	public String joinConfirm(@ModelAttribute("member") Member member){
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
}
