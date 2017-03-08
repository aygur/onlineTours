package com.naraikin.onlinetours.controllers.servlets.tour;

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
import java.sql.Date;

/**
 * Created by dmitrii on 28.02.17.
 */
//@WebServlet(name = "EditTourServlet", urlPatterns = "/tour/edit")
public class EditTourServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EditTourServlet.class);

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
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Tour tour = tourService.getTourById(id);
            req.setAttribute("tour", tour);
            req.getRequestDispatcher("/tour/edit.jsp").forward(req, resp);
        } catch (TourServiceException | NumberFormatException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Tour tour = new Tour(
                Integer.parseInt(req.getParameter("idtur")),
                Date.valueOf(req.getParameter("dateStart")),
                Date.valueOf(req.getParameter("dateFinish")),
                req.getParameter("tur_type"),
                req.getParameter("menu_type"),
                Double.parseDouble(req.getParameter("cost")),
                Short.parseShort(req.getParameter("booking")),
                req.getParameter("hotel"),
                req.getParameter("city"),
                Short.parseShort(req.getParameter("deleted")));
        try {
            tourService.updateTour(tour);
            resp.sendRedirect("/dashboard");
        } catch (TourServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }
    }
}
