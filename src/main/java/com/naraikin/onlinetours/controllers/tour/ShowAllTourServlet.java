package com.naraikin.onlinetours.controllers.tour;

import com.naraikin.onlinetours.common.exception.ClientDAOException;
import com.naraikin.onlinetours.common.exception.TourServiceException;
import com.naraikin.onlinetours.models.pojo.Tour;
import com.naraikin.onlinetours.services.TourService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dmitrii on 24.02.17.
 */
@WebServlet(name = "ShowAllTuorServlet", urlPatterns = "/dashboard")
public class ShowAllTourServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(ShowAllTourServlet.class);

    private TourService tourService;

    @Autowired
    public void setTourService(TourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tour> tourList = null;
        try {
            tourList = tourService.getAllTour();
            req.setAttribute("tourList", tourList);
            logger.trace("Get list of tours");
            getServletContext().getRequestDispatcher("/tour/list.jsp").forward(req, resp);

        } catch (TourServiceException e) {
            logger.trace(e);
            resp.sendRedirect("/error");
        }
    }
}
