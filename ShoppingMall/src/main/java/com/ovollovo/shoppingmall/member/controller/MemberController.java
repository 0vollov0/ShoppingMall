package com.ovollovo.shoppingmall.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.ovollovo.shoppingmall.goods.Goods;
import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.member.ShoppingBasket;
import com.ovollovo.shoppingmall.service.GoodsService;
import com.ovollovo.shoppingmall.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private GoodsService goodsService;

	@ModelAttribute("contextPath")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody JsonObject login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session,
			HttpServletResponse response) {
		return memberService.loginMember(id, pw,session);
	}

	@RequestMapping(value = "/joinForm")
	public String joinForm(Model model) {
		model.addAttribute("imagePath","C:\\Users\\OvollovO\\Documents\\GitHub\\ShoppingMall\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ShoppingMall\\");
		return "member/joinForm";
	}

	
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public @ResponseBody JsonObject join(@ModelAttribute("member") Member member,
			@RequestParam("userCaptchaKey") String userCaptchaKey,@RequestParam("captchaKey")String captchaKey,HttpServletResponse response) {
		JsonObject jsonObject = null;;
		try {
			jsonObject = memberService.joinMember(member,captchaKey,userCaptchaKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
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
	
	@RequestMapping(value = "getCaptcha", method = RequestMethod.GET)
	public @ResponseBody JsonObject getCaptcha() {
		return memberService.getCaptchaKeyNImage();
	}
	
	@RequestMapping(value = "deleteCaptcha", method = RequestMethod.GET)
	public @ResponseBody void deleteCaptcha(@RequestParam("image")String image) {
		memberService.deleteCaptchaImage(image);
	}
	
	@RequestMapping(value = "shoppingBasket", method = RequestMethod.POST)
	public @ResponseBody JsonObject shoppingBasket(@RequestParam("code")String code,HttpSession session) {
		Map<String, ShoppingBasket> shoppingBasketList = (Map<String, ShoppingBasket>) session.getAttribute("shoppingBasketList");
		if (shoppingBasketList == null) {
			System.out.print("new");
			shoppingBasketList = new HashMap<String, ShoppingBasket>();
		}
		if (shoppingBasketList.containsKey(code)) {
			System.out.print("upcont");
			shoppingBasketList.get(code).upCount();
		}else {
			System.out.print("put");
			shoppingBasketList.put(code, new ShoppingBasket(goodsService.getGoods(code)));
		}
		session.setAttribute("shoppingBasketList", shoppingBasketList);
		return memberService.getShoppingBasketResultJson(shoppingBasketList.size());
	}
	@RequestMapping(value = "shoppingBasketList", method = RequestMethod.GET)
	public String shoppingBasket(Model model,HttpSession session) {
		/*
		ArrayList<Goods> shoppingBasketList = (ArrayList<Goods>) session.getAttribute("shoppingBasketList");
		model.addAttribute("shoppingBasketList", shoppingBasketList);
		*/
		Map<String, ShoppingBasket> shoppingBasketList = (Map<String, ShoppingBasket>) session.getAttribute("shoppingBasketList");
		model.addAttribute("shoppingBasketList", shoppingBasketList);
		return "member/shoppingBasketList";
	}
	
	@RequestMapping(value = "deleteShoppingBasket", method = RequestMethod.GET)
	public @ResponseBody JsonObject deleteShoppingBasket(@RequestParam("code")String code,HttpSession session) {
		Map<String, ShoppingBasket> shoppingBasketList = (Map<String, ShoppingBasket>) session.getAttribute("shoppingBasketList");
		
		shoppingBasketList.remove(code);
		
		session.setAttribute("shoppingBasketList", shoppingBasketList);
		
		return memberService.getDeleteShoppingBasketResultJson(code);
	}
}
