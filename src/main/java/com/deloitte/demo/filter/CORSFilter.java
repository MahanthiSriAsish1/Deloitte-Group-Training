package com.deloitte.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        // Handle preflight requests
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return; // Preflight request, return response immediately
        }

        chain.doFilter(request, response); // Proceed with the request
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }
}
