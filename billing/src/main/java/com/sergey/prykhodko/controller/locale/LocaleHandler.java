package com.sergey.prykhodko.controller.locale;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import static com.sergey.prykhodko.system.ClassName.getCurrentClassName;

@WebServlet("/locale")
public class LocaleHandler extends HttpServlet {
    private static Logger logger = Logger.getLogger(getCurrentClassName());

    public LocaleHandler() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String language = request.getParameter("locale");
        Locale locale = new Locale(language);
        Config.set(session, Config.FMT_LOCALE, locale);
        PrintWriter out = response.getWriter();
        out.println(locale);
    }
}
