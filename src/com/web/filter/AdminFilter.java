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
 * 这个过滤器是用来过滤所有进入admin这个包的指示
 * 如果session中没有admin这个key，那么就不可以进入其中的任何一个文件
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
		 * 1.得到session
		 * 2.判断session中是否有admin或者username，如果有，放行
		 * 3.如果没有，转发到login.jsp，并且显示错误信息
		 * 
		 */
		/*
		 * 1.得到session。因为上面的request不是我们要的http的request，所以先要强转
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
