package servlets;

import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.dao.interfaces.DAOFactory;
import com.sergey.prykhodko.dao.interfaces.UserDAO;
import com.sergey.prykhodko.users.User;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collector;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("loginForm:login");
        String password = request.getParameter("loginForm:password");

        DAOFactory factory = DAOFactory.getDAOFactory(FactoryType.MySQL);

        UserDAO userDAO = null;
        try {
            userDAO = factory.getUserDAO();
        } catch (SQLException | NamingException e ) {
            e.printStackTrace();
        }
        User client = null;
        try {
             client = userDAO.getUser(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (client != null){
            response.sendRedirect("clientCabinet.jsp");
        }
        else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
        
    }
}
