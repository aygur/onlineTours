package com.naraikin.onlinetours.controllers;

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

/**
 * Created by dmitrii on 23.02.17.
 */

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(LoginServlet.class);


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
        req.getRequestDispatcher("client/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("POST");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        try {
            Client client = clientService.authorize(login, password);
            if (client.getIdclient() != 0) {
                logger.trace("authorized");
                resp.sendRedirect("/dashboard");
                session.setAttribute("login", login);
                session.setAttribute("id", client.getIdclient());
            } else {
                logger.trace("not authorized " + login);
                req.setAttribute("error","Неверный логин или пароль");
                req.getRequestDispatcher("client/login.jsp").forward(req, resp);
            }
        } catch (ClientServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }

    }
}
