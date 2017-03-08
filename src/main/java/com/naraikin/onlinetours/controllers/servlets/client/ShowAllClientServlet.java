package com.naraikin.onlinetours.controllers.servlets.client;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.services.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
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
//@WebServlet(name = "ShowAllClientServlet", urlPatterns = "/client")
public class ShowAllClientServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(ShowAllClientServlet.class);

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("GET");
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("login");
        try {
            List<Client> clients = clientService.getAllClient();
            req.setAttribute("clients", clients);
            req.getRequestDispatcher("/client/list.jsp").forward(req, resp);
        } catch (ClientServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }

    }
}
