package com.sergey.prykhodko.filters;

import com.sergey.prykhodko.model.users.User;
import com.sergey.prykhodko.model.users.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdministratorFilter extends BaseFilter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null || user.getRole() != UserRole.ADMIN){
            httpServletResponse.sendRedirect("index.jsp");
        }
            filterChain.doFilter(servletRequest, servletResponse);
    }

}
