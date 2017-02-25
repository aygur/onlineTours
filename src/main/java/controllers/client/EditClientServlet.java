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
import java.sql.Date;

/**
 * Created by dmitrii on 25.02.17.
 */
@WebServlet(name = "EditClientServlet", urlPatterns = "/client/edit")
public class EditClientServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(EditClientServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        try {
            Client client = ClientService.getClientById(id);
            req.setAttribute("client", client);
            req.getRequestDispatcher("/client/edit.jsp").forward(req, resp);
        } catch (ClientDAOException e) {
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
        System.out.println(req.getParameter("birthDate"));
        client.setBirthDate(Date.valueOf(req.getParameter("birthDate")));
        client.setAddress(req.getParameter("address"));
        client.setGender(req.getParameter("gender"));
        client.setLogin(req.getParameter("login"));
        client.setPassword(req.getParameter("password"));
        client.setEmail(req.getParameter("email"));
        client.setRole(req.getParameter("role"));

        try {
            ClientService.update(client);
            resp.sendRedirect("/client");
        } catch (ClientDAOException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }
    }
}
