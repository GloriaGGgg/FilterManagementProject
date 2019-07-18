package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 这个servlet时专门用来处理login的
 * @author Gloria
 *
 */
public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取登陆的用户名
		 * 2.若是用户名是：admin，那么就是管理员
		 * 3.若是用户名是其他的，则默认为user
		 * 4.把用户名信息保存到session中
		 * 5.把最后的登陆信息转发到index.jsp中
		 */
		String username=request.getParameter("username");
		
		//下面两个session设置，就是前面key不一样
		if(username.equals("admin")){
			//如果是admin，那么就是administrator登陆的
			request.getSession().setAttribute("admin", username);
		}else{
			//其他情况就是普通的member
			request.getSession().setAttribute("username", username);
		}
		
		//转发到index.jsp页面中
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		

	}   

}
