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

/**
 * Created by dmitrii on 03.03.17.
 */
//@WebServlet(name = "DeleteTourServlet", urlPatterns = "/tour/delete")
public class DeleteTourServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(DeleteTourServlet.class);

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("idtur"));
        Short deleted = Short.parseShort(req.getParameter("deleted"));
        if(deleted == 1){
            deleted = 0;
        } else {
            deleted = 1;
        }
        Tour tour = new Tour(id, deleted);
        try {
            tourService.setDeleteTour(tour);
            resp.sendRedirect("/dashboard");

        } catch (TourServiceException e) {
            logger.error(e);
            resp.sendRedirect("/error");
        }

    }
}
