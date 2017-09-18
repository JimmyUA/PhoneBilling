package com.sergey.prykhodko.filters;

import com.sergey.prykhodko.model.users.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = "login")
public class LoginFilter extends BaseFilter {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User)request.getSession().getAttribute("user");
        if (user != null){
            response.sendRedirect("clientCabinet.jsp");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
