package controllers.client;

import common.ClientDAOException;
import models.dao.ClientDAO;
import models.pojo.Client;
import org.apache.log4j.Logger;
import services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmitrii on 26.02.17.
 */
@WebServlet(name = "BlockClientServlet", urlPatterns = "/client/block")
public class BlockClientServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(BlockClientServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ClientService.setClientBlocked(Integer.parseInt(req.getParameter("id")));
            logger.trace("blocked" + Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("/client");
        } catch (ClientDAOException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }
    }
}
