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

/**
 * Created by dmitrii on 28.02.17.
 */
@WebServlet(name = "LKClientServlet", urlPatterns = "/LKClient")
public class LKClientServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(LKClientServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            Client client = ClientService.getClientById((Integer) session.getAttribute("id"));
            req.setAttribute("client", client);
            req.getRequestDispatcher("/client/lk.jsp").forward(req, resp);
        } catch (ClientServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }
    }
}