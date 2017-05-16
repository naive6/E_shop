package cn.it.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		if(req.getSession().getAttribute("user")==null){
			String goURL=req.getServletPath();
			System.out.println("servletPath: "+goURL);//获取用户想要去的地址
			String param=req.getQueryString();//获得地址中携带的参数
			System.out.println("param:"+param);
			if(param!=null){
				goURL=goURL+"?"+param;
			}
			req.getSession().setAttribute("goURL", goURL);
			req.getSession().setAttribute("error", "您还未登录，请先登录");
			res.sendRedirect(req.getContextPath()+"/ulogin.jsp");
			System.out.println("contextPath: "+req.getContextPath());
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
