package controllers;

import common.ClientDAOException;
import org.apache.log4j.Logger;
import services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        try {
            if(ClientService.registration(login, password, email)){
                logger.trace("registration");
                resp.sendRedirect("/login");
            }else{
                logger.trace("not registration");
                req.setAttribute("error", "Допущена ошибка при вводе данных");
                req.getRequestDispatcher("client/registration.jsp").forward(req, resp);
            }
        } catch (ClientDAOException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }
    }
}
