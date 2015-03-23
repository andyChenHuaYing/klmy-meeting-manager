package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.util.ConstantParam;
import com.util.UserHolder;
import com.vo.UserVO;

/**
 * 
 * @className: RequestFilter 
 * @author: gu.xiaogang
 * @description: 请求过滤器
 * @date: 2014年9月10日
 */
public class RequestFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * @description:将用户信息存到UserHolder中，可以通过UserHolder随时随地取到 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();		
		UserVO userVO = (UserVO)session.getAttribute(ConstantParam.USER_INFO);
		if(userVO!=null){
			UserHolder.setUserContext(userVO);
		}
		chain.doFilter(request, response);
		//关闭连接
		UserHolder.setUserContext(null);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
