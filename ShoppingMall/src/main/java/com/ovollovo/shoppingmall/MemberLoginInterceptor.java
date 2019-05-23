package com.ovollovo.shoppingmall;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MemberLoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) {
		HttpSession session = request.getSession();
		if (session != null) {
			Object obj = session.getAttribute("member");
			if (obj != null) {
				return true;
			}
		}
		try {
			response.sendRedirect(request.getContextPath()+"/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
