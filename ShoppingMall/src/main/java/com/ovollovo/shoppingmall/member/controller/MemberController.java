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
			System.out.println("���̵� �Ǵ� ��й�ȣ�� ���� �ʽ��ϴ�.");
			break;
		case 2:
			System.out.println("���� ������ �ʿ��� ȸ���Դϴ�.");
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
			System.out.println("ȸ������ ����");
		}else {
			System.out.println("ID �Ǵ� Email �ߺ�");
		}
		return "index";
	}
	*/
	@RequestMapping(value = "/join" ,method = RequestMethod.POST)
	public String join(@ModelAttribute("member") Member member,Model model) {
		try {
			switch (memberService.joinMember(member)) {
			case 0:
				System.out.println("ȸ������ ����");
				break;
			case 1:
				System.out.println("ID �ߺ�");
				break;
			case 2:
				System.out.println("Email �ߺ�");
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
			System.out.println("�������� ����");
			break;
		case 1:
			System.out.println("�������� ����");
			break;
		default:
			break;
		}
		
		return "redirect:/";
	}
}
