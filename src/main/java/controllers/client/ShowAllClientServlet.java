package controllers.client;

import common.ClientDAOException;
import common.ClientServiceException;
import models.pojo.Client;
import org.apache.log4j.Logger;
import services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by dmitrii on 24.02.17.
 */
@WebServlet(name = "ShowAllClientServlet", urlPatterns = "/client")
public class ShowAllClientServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(ShowAllClientServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("GET");
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("login");
        try {
            List<Client> clients = ClientService.getAllClient();
            req.setAttribute("clients", clients);
            req.getRequestDispatcher("/client/list.jsp").forward(req, resp);
        } catch (ClientServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }

    }
}
