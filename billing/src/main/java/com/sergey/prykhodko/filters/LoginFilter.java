package com.sergey.prykhodko.filters;

import com.sergey.prykhodko.users.User;

import javax.servlet.*;
import java.io.IOException;

public class LoginFilter extends BaseFilter {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String login = servletRequest.getParameter("loginForm:login");
        String password = servletRequest.getParameter("loginForm:password");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
