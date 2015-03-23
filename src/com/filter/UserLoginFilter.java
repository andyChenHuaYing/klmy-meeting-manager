package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.ConstantParam;

/**
 * 
 * @className: UserLoginFilter 
 * @author: gu.xiaogang
 * @description: 用户登录过滤器
 * @date: 2014年9月10日
 */
public class UserLoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * @description: 如果未登录则跳转到登录页面
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();		
		String target = httpRequest.getRequestURI();
		if(target.endsWith("user.spr") || target.endsWith("userFM.spr") || target.indexOf("login_new.jsp")>=0 || target.indexOf("logon.spr")>=0 || target.indexOf("toAddUser")>=0
				|| target.indexOf("person.spr")>=0 || target.indexOf("dpaq.spr")>=0 || target.indexOf("weChat.spr")>=0 || target.indexOf("sign.spr")>=0 || target.indexOf("tableConfig.spr")>=0 || target.indexOf("buffer.spr")>=0){ 
			chain.doFilter(request, response);
		}else{
			if (null == session.getAttribute(ConstantParam.USER_INFO)) {
				System.out.println("session过期或为空");
				resp.sendRedirect(httpRequest.getContextPath()+"/login_new.jsp");
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
