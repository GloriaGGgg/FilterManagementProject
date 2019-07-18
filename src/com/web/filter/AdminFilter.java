package com.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * ����������������������н���admin�������ָʾ
 * ���session��û��admin���key����ô�Ͳ����Խ������е��κ�һ���ļ�
 * 
 * @author Gloria
 *
 */
public class AdminFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 1.�õ�session
		 * 2.�ж�session���Ƿ���admin����username������У�����
		 * 3.���û�У�ת����login.jsp��������ʾ������Ϣ
		 * 
		 */
		/*
		 * 1.�õ�session����Ϊ�����request��������Ҫ��http��request��������Ҫǿת
		 */
		HttpServletRequest req=(HttpServletRequest) request;
		String name=(String) req.getSession().getAttribute("admin");

		if(name !=null){
			chain.doFilter(request, response);
			return;
		}else{
			req.setAttribute("msg", "Please be the admin first then visit this page!");
			req.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
