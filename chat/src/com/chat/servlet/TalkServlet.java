package com.chat.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TalkServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 获取某个用户发送消息
		String content = request.getParameter("content");

		// 发送空消息就出错
		if (content.trim().isEmpty() || content == null) {
			request.setAttribute("error", "不能发送空消息");
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request,
					response);
		} else {
			// 判断全局变量中是否有聊天室这个项
			if (request.getServletContext().getAttribute("chatroom") == null) {
				// 没有聊天室的话添加聊天室
				List<String> listContent = new ArrayList<String>();
				request.getServletContext().setAttribute("chatroom",
						listContent);
			}
			// 获取聊天室
			List<String> chatroom = (List<String>) request.getServletContext()
					.getAttribute("chatroom");
			// 设置用户说的消息
			String say = "" + request.getSession().getAttribute("username")
					+ ": " + content;
			// 添加到聊天室中
			chatroom.add(say);
			request.getServletContext().setAttribute("chatroom", chatroom);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/index.jsp");
			dispatcher.forward(request, response);
		}
	}
}
