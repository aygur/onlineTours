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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by dmitrii on 24.02.17.
 */
@WebServlet(name = "ShowAllTuorServlet", urlPatterns = "/dashboard")
public class ShowAllTourServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(ShowAllTourServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tour> tourList = null;
        try {
            tourList = TourService.getAllTour();
            req.setAttribute("tourList", tourList);
            logger.trace("Get list of tours");
            getServletContext().getRequestDispatcher("/tour/list.jsp").forward(req, resp);

        } catch (ClientDAOException e) {
            logger.trace(e);
            resp.sendRedirect("/error");
        }
    }
}
