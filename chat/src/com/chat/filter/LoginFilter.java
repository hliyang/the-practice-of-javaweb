package com.chat.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 访问loginServlet时拦截，进行登陆验证
 * 
 * @author hliyang
 * 
 */
public class LoginFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// 输入的密码只能是123，否则密码不正确
		String password = req.getParameter("password");
		if (!("123".equals(password))) {
			request.setAttribute("error", "密码不正确...");
			request.getRequestDispatcher("/login.jsp").forward(req, resp);
			;
		} else {
			// 执行放行操作
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
	}
}
