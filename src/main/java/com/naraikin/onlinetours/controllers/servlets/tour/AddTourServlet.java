package com.naraikin.onlinetours.controllers.servlets.tour;

import com.naraikin.onlinetours.common.exception.TourServiceException;
import com.naraikin.onlinetours.models.pojo.Tour;
import com.naraikin.onlinetours.services.interfaces.TourService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by dmitrii on 03.03.17.
 */
//@WebServlet(name = "AddTourServlet", urlPatterns = "/tour/add")
public class AddTourServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(AddTourServlet.class);

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
        req.getRequestDispatcher("/tour/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Tour tour = new Tour(
                0,
                Date.valueOf(req.getParameter("dateStart")),
                Date.valueOf(req.getParameter("dateFinish")),
                req.getParameter("tur_type"),
                req.getParameter("menu_type"),
                Double.parseDouble(req.getParameter("cost")),
                (short) 0,
                req.getParameter("hotel"),
                req.getParameter("city"),
                (short) 0);
        try {
            tourService.createTour(tour);
            resp.sendRedirect("/dashboard");
        } catch (TourServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }
    }
}
