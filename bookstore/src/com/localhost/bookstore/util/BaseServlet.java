package com.localhost.bookstore.util;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet的父类，接受method参数执行servlet中不同的方法
 * 
 * @author hliyang
 * 
 */
@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");// 处理响应编码
		request.setCharacterEncoding("UTF-8");
		/**
		 * 1. 获取method参数 
		 * 2. 把方法名称变成Method类的实例对象
		 * 3. 通过invoke()来调用这个方法
		 */
		String methodName = request.getParameter("method");
		Method method = null;
		try {
			method = this.getClass().getMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("您要调用的方法：" + methodName + "不存在！", e);
		}
		try {
			// 返回的路径 :forward:/jsps/left.jsp
			String result = (String) method.invoke(this, request, response);
			if (result != null && !result.trim().isEmpty()) {
				// 如果请求处理的方法返回值不为空
				int index = result.indexOf(":");// 获取第一个冒号的位置
				if (index == -1) {
					// 如果没有冒号，就使用请求转发
					request.getRequestDispatcher(result).forward(request,
							response);
				} else {
					// 如果存在冒号
					String start = result.substring(0, index);// 分割出前缀
					String path = result.substring(index + 1);// 分割出路径
					if (start.equals("forward")) {// 前缀为f表示转发
						request.getRequestDispatcher(path).forward(request,
								response);
					} else if (start.equals("redirect")) {// 前缀为r表示重定向
						response.sendRedirect(request.getContextPath() + path);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
