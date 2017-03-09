package com.naraikin.onlinetours.controllers.servlets.client;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.services.interfaces.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dmitrii on 28.02.17.
 */
//@WebServlet(name = "LKClientServlet", urlPatterns = "/LKClient")
public class LKClientServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(LKClientServlet.class);


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
        HttpSession session = req.getSession();
        try {
            Client client = clientService.getClientById((Integer) session.getAttribute("id"));
            req.setAttribute("client", client);
            req.getRequestDispatcher("/client/lk.jsp").forward(req, resp);
        } catch (ClientServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }
    }
}