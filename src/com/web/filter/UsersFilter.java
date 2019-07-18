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
 * 这个过滤器是用来过滤所有进入users包的指令
 * 只有session中有username和admin这两个key才可以进入
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
		
		/*
		 * 2.进行判断
		 */
		if(name !=null){
			//说明登陆的是admin,直接放行，并且return结束
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
