package servlets.admin;


import com.sergey.prykhodko.dao.FactoryType;
import com.sergey.prykhodko.managers.UserService;
import com.sergey.prykhodko.managers.UsersManager;
import com.sergey.prykhodko.managers.commands.ChangeClientStatus;
import com.sergey.prykhodko.users.Client;
import org.apache.log4j.Logger;
import servlets.LoginServlet;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/activate")
public class ClientStatusChanging extends HttpServlet{
    private static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("clientID"));
        Client client = null;
        try {
            client = (Client) new UsersManager().getUserByID(id, FactoryType.MySQL);
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        try {
            UserService.executeCommand(new ChangeClientStatus(client));
        } catch (SQLException | NamingException e) {
            logger.error(e);
        }
        response.sendRedirect("/clientsList");

    }
}
