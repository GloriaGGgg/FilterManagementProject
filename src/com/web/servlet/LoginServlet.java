package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ���servletʱר����������login��
 * @author Gloria
 *
 */
public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.��ȡ��½���û���
		 * 2.�����û����ǣ�admin����ô���ǹ���Ա
		 * 3.�����û����������ģ���Ĭ��Ϊuser
		 * 4.���û�����Ϣ���浽session��
		 * 5.�����ĵ�½��Ϣת����index.jsp��
		 */
		String username=request.getParameter("username");
		
		//��������session���ã�����ǰ��key��һ��
		if(username.equals("admin")){
			//�����admin����ô����administrator��½��
			request.getSession().setAttribute("admin", username);
		}else{
			//�������������ͨ��member
			request.getSession().setAttribute("username", username);
		}
		
		//ת����index.jspҳ����
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		

	}   

}
