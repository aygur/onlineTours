package controllers.tour;

import common.ClientDAOException;
import models.pojo.Tour;
import org.apache.log4j.Logger;
import services.TourService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmitrii on 28.02.17.
 */
@WebServlet(name = "EditTourServlet", urlPatterns = "/tour/edit")
public class EditTourServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EditTourServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Tour tour = TourService.getTourById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("tour", tour);
            req.getRequestDispatcher("/tour/edit.jsp").forward(req, resp);
        } catch (ClientDAOException e) {
            logger.error(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
