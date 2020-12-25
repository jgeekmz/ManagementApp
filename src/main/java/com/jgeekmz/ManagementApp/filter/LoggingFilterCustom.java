package com.jgeekmz.ManagementApp.filter;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*@Component
@WebFilter(urlPatterns = "/index")
public class LoggingFilterCustom implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println(request.getSession().getAttribute("username") + ": " + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}*/
