package com.ovollovo.shoppingmall;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ovollovo.shoppingmall.service.AdminService;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	AdminService adminService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) {
		HttpSession session = request.getSession();
		if (adminService.checkAdmin(session)) {
			return true;
		}
		try {
			response.sendRedirect(request.getContextPath()+"/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
