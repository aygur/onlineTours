package com.naraikin.onlinetours.controllers.servlets.client;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.services.interfaces.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmitrii on 26.02.17.
 */
//@WebServlet(name = "BlockClientServlet", urlPatterns = "/client/block")
public class BlockClientServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(BlockClientServlet.class);



    @Autowired
    @Qualifier("ClientServiceImpl")
    private ClientService clientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Client client = new Client();
            client.setIdclient(Integer.parseInt(req.getParameter("id")));
            Short block = Short.parseShort(req.getParameter("block"));
            if (block == 0){
                block = 1;
            }else {
                block = 0;
            }

            client.setBlocked(block);
            clientService.setClientBlocked(client);
            logger.trace("block" + Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("/client");
        } catch (ClientServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }
    }
}
