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
 * ����������������������н���users����ָ��
 * ֻ��session����username��admin������key�ſ��Խ���
 * 
 * @author Gloria
 *
 */
public class UsersFilter implements Filter {
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
		
		/*
		 * 2.�����ж�
		 */
		if(name !=null){
			//˵����½����admin,ֱ�ӷ��У�����return����
			chain.doFilter(request, response);
			return;
		}
		
		name=(String)req.getSession().getAttribute("username");
		if(name !=null){
			chain.doFilter(request, response);
			return;
		}else{
			req.setAttribute("msg", "Please be the user or admin first then visit this page!");
			req.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
