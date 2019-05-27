package com.ovollovo.shoppingmall.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginedInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) {
		HttpSession session = request.getSession();
		if (session != null) {
			Object obj = session.getAttribute("member");
			if (obj != null) {
				try {
					response.setContentType("text/html; charset=UTF-8");
					 
					PrintWriter out = response.getWriter();
					 
					out.println("<script>alert('잘못된 접근입니다.'); location.href='"+request.getContextPath()+"/member/loginForm';</script>");
					 
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		}
		return true;
	}

}
