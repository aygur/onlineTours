package com.naraikin.onlinetours.controllers.client;

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
import java.sql.Date;

/**
 * Created by dmitrii on 25.02.17.
 */
@WebServlet(name = "EditClientServlet", urlPatterns = "/client/edit")
public class EditClientServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(EditClientServlet.class);

    @Autowired
    private ClientService clientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Client client = null;
        try {
            if(req.getParameter("id") != null){
                Integer id = Integer.parseInt(req.getParameter("id"));
                client = clientService.getClientById(id);
            } else {
                HttpSession session = req.getSession();
                client = clientService.getClientById((Integer) session.getAttribute("id"));
            }

            req.setAttribute("client", client);
            req.getRequestDispatcher("/client/edit.jsp").forward(req, resp);
        } catch (ClientServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = new Client();
        req.setCharacterEncoding("UTF-8");
        client.setIdclient(Integer.parseInt(req.getParameter("id")));
        client.setLastName(req.getParameter("lastName"));
        client.setFirstName(req.getParameter("firstName"));
        client.setPhone(req.getParameter("phone"));
        client.setDoc(req.getParameter("doc"));
        client.setBirthDate(Date.valueOf(req.getParameter("birthDate")));
        client.setAddress(req.getParameter("address"));
        client.setGender(req.getParameter("gender"));
        client.setLogin(req.getParameter("login"));
        client.setPassword(req.getParameter("password"));
        client.setEmail(req.getParameter("email"));
        client.setRole(req.getParameter("role"));

        try {
            clientService.update(client);
            resp.sendRedirect("/client");
        } catch (ClientServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }
    }
}
