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
			System.out.println("servletPath: "+goURL);//��ȡ�û���Ҫȥ�ĵ�ַ
			String param=req.getQueryString();//��õ�ַ��Я���Ĳ���
			System.out.println("param:"+param);
			if(param!=null){
				goURL=goURL+"?"+param;
			}
			req.getSession().setAttribute("goURL", goURL);
			req.getSession().setAttribute("error", "����δ��¼�����ȵ�¼");
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
