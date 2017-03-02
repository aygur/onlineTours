package controllers;

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
import java.io.IOException;
import java.sql.Date;

/**
 * Created by dmitrii on 23.02.17.
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegistrationServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("client/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on post");
        req.setCharacterEncoding("UTF-8");
        Client client = new Client(0,
                req.getParameter("lastName"),
                req.getParameter("firstName"),
                req.getParameter("phone"),
                Date.valueOf(req.getParameter("birthDate")),
                req.getParameter("doc"),
                req.getParameter("address"),
                req.getParameter("gender"),
                req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("email"),
                "user",
                (short) 0);

        try {
            if(ClientService.registration(client)) {
                logger.trace("registration ok");
                req.setAttribute("error", "Регистрация успешна! Войдите в систему");
                req.getRequestDispatcher("client/login.jsp").forward(req, resp);
            }else{
                logger.trace("not registration");
                req.setAttribute("error", "Допущена ошибка при вводе данных");
                req.getRequestDispatcher("client/registration.jsp").forward(req, resp);
            }
        } catch (ClientServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }
    }
}
