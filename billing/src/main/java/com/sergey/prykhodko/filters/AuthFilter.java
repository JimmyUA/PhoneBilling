package com.sergey.prykhodko.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class AuthFilter extends BaseFilter {
    private static Logger logger = Logger.getLogger(AuthFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        //if is ADMIN go to Admin cabinet ADMIN previleges are granted
        //if CLIENT go to Client cabinet
        //if GUEST - to registration or index
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
