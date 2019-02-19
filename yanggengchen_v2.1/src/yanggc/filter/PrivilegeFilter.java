package yanggc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yanggc.domain.User;

public class PrivilegeFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//判断用户属否登录
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		User user = (User) req.getSession().getAttribute("user");
		if(user==null){
			//跳到登录页面
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
			return;
		}
		
		chain.doFilter(req, resp);
		
		
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
