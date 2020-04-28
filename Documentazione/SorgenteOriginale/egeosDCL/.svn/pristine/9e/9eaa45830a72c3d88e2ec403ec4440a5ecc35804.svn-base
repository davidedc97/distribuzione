package it.gis.egeosDCL.server.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

public class CacheControlFilter implements Filter {

	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
	    long cacheAge = 31536000;
		long expiry = new Date().getTime() + cacheAge;

	    HttpServletResponse httpResponse = (HttpServletResponse)response;
	    httpResponse.setDateHeader("Expires", expiry);
	    httpResponse.setHeader("Cache-Control", "max-age="+ cacheAge);

	    chain.doFilter(request, response);
		
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
