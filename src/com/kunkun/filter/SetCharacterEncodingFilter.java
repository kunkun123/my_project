package com.kunkun.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SetCharacterEncodingFilter implements Filter {
    private FilterConfig config;
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String encoding = config.getInitParameter("encoding");
        if(encoding==null){
        	encoding = "UTF-8";
        }
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=UTF-8");
        chain.doFilter(request, response);
	}

	
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
