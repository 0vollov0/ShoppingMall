package com.ovollovo.shoppingmall.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.ovollovo.shoppingmall.member.Member;
import com.ovollovo.shoppingmall.member.ShoppingBasket;
import com.ovollovo.shoppingmall.service.GoodsService;
import com.ovollovo.shoppingmall.service.MemberService;
import com.ovollovo.shoppingmall.service.OrderService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private OrderService orderService;

	@ModelAttribute("contextPath")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	@RequestMapping(value = "/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody JsonObject login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session,HttpServletResponse response) {
		return memberService.loginMember(id, pw,session);
	}

	@RequestMapping(value = "/joinForm")
	public String joinForm(Model model) {
		return "member/joinForm";
	}

	
	@RequestMapping(value = "join", method = RequestMethod.POST)
	public @ResponseBody JsonObject join(@ModelAttribute("member") Member member,
			@RequestParam("userCaptchaKey") String userCaptchaKey,@RequestParam("captchaKey")String captchaKey,HttpServletResponse response) {
		JsonObject jsonObject = null;;
		try {
			jsonObject = memberService.joinMember(member,captchaKey,userCaptchaKey);
		} catch (Exception e) {
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
	public void joinConfirm(Model model,@ModelAttribute("member") Member member,HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		String message = null;
		switch (memberService.updateAuthstatus(member)) {
		case 0:
			message = "메일인증 성공";
			break;
		case 1:
			message = "메일인증 실패";
			break;
		default:
			break;
		}
		
		PrintWriter out;
		try {
			out = response.getWriter();
			
			out.println("<script>alert('"+message+"'); location.href='"+request.getContextPath()+"/'</script>");
			 
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/modifyForm")
	public String modifiedForm() {
		return "member/modifyForm";
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public @ResponseBody JsonObject modify(@RequestParam("id") String id,@RequestParam("pw") String pw,@RequestParam("newpw") String newpw) {
		return memberService.modifyMember(id, pw, newpw);
	}
	
	@RequestMapping(value = "getCaptcha", method = RequestMethod.GET)
	public @ResponseBody JsonObject getCaptcha() {
		return memberService.getCaptchaKeyNImage();
	}
	
	@RequestMapping(value = "deleteCaptcha", method = RequestMethod.GET)
	public @ResponseBody void deleteCaptcha(@RequestParam("image")String image) {
		memberService.deleteCaptchaImage(image);
	}
	
	@RequestMapping(value = "/withdrawal")
	public String withdrawal(HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		session.invalidate();
		memberService.deleteMember(member.getId());
		return "redirect:/";
	}
	
	@RequestMapping(value = "shoppingBasket", method = RequestMethod.POST)
	public @ResponseBody JsonObject shoppingBasket(@RequestParam("code")String code,HttpSession session) {
		Map<String, ShoppingBasket> shoppingBasketList = (Map<String, ShoppingBasket>) session.getAttribute("shoppingBasketList");
		if (shoppingBasketList == null) {
			shoppingBasketList = new HashMap<String, ShoppingBasket>();
		}
		if (shoppingBasketList.containsKey(code)) {
			shoppingBasketList.get(code).upCount();
		}else {
			shoppingBasketList.put(code, new ShoppingBasket(goodsService.getGoods(code)));
		}
		session.setAttribute("shoppingBasketList", shoppingBasketList);
		return memberService.getShoppingBasketResultJson(shoppingBasketList.get(code).getGoods().getName());
	}
	
	@RequestMapping(value = "basketUpDown", method = RequestMethod.POST)
	public @ResponseBody JsonObject basketUpDown(@RequestParam("code")String code,@RequestParam("action") int action,HttpSession session) {
		System.out.println(code+""+action);
		Map<String, ShoppingBasket> shoppingBasketList = (Map<String, ShoppingBasket>) session.getAttribute("shoppingBasketList");
		if (shoppingBasketList != null && shoppingBasketList.size() > 0) {
			if (action == -1) {
				shoppingBasketList.get(code).downCount();
			}else if(action == 1) {
				shoppingBasketList.get(code).upCount();
			}
			session.setAttribute("shoppingBasketList", shoppingBasketList);
		}
		JsonObject json = new JsonObject();
		json.addProperty("id", code);
		json.addProperty("value", shoppingBasketList.get(code).getCount());
		return json;
	}
	
	@RequestMapping(value = "shoppingBasketList", method = RequestMethod.GET)
	public String shoppingBasket(Model model,HttpSession session) {
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
	
	@RequestMapping(value = "memberOrderList")
	public String memberOrderList(Model model,HttpSession session,@RequestParam("page") int page) {
		if (page <= 0) {
			page = 1;
		}
		Member member = (Member) session.getAttribute("member");
		model.addAttribute("orderList", orderService.getOrderList(member.getId(), page));
		if (page <= 0) {
			page = 1;
		}

		int maxPage = orderService.getCurrentMaxPage(page,member.getId());
		int minPage;
		if (maxPage%10 > 0) {
			minPage = maxPage/10+1;
		}else {
			minPage = (maxPage-1)/10+1;
		}
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("minPage", minPage);
		if (page > maxPage) {
			model.addAttribute("currentPage", maxPage);
		}else {
			model.addAttribute("currentPage", page);
		}
		return "member/memberOrderList";
	}
}
