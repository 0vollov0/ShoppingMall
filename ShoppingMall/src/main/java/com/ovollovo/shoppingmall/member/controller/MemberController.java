package com.ovollovo.shoppingmall.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}
	@RequestMapping(value = "/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}
}
