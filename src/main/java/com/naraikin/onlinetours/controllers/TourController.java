package com.naraikin.onlinetours.controllers;

import com.naraikin.onlinetours.common.exception.TourServiceException;
import com.naraikin.onlinetours.models.pojo.Tour;
import com.naraikin.onlinetours.services.TourService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 07.03.17.
 */
@Controller
public class TourController {

    static Logger logger = Logger.getLogger(TourController.class);

    private TourService tourService;
    @Autowired
    public void setTourService(TourService tourService) {
        this.tourService = tourService;
    }


    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashbordPage(Model model){
        try {
            List<Tour> tourList = tourService.getAllTour();
            model.addAttribute("tourList", tourList);
            logger.trace("Get list of tours");

        } catch (TourServiceException e) {
            logger.trace(e);
            return "redirect:"+"/error";
        }
        return "tour/list";
    }


    @RequestMapping(value = "/tour/add", method = RequestMethod.GET)
    public String addTourGetPage(Model model){
        return "tour/add";
    }

    @RequestMapping(value = "/tour/add", method = RequestMethod.POST)
    public String addTourPostPage(Model model,
                                  @RequestParam(name = "dateStart") String dateStart,
                                  @RequestParam(name = "dateFinish") String dateFinish,
                                  @RequestParam(name = "tur_type") String tur_type,
                                  @RequestParam(name = "menu_type") String menu_type,
                                  @RequestParam(name = "cost") Double cost,
                                  @RequestParam(name = "hotel") String hotel,
                                  @RequestParam(name = "city") String city){
        Tour tour = new Tour(
                0,
                Date.valueOf(dateStart),
                Date.valueOf(dateFinish),
                tur_type,
                menu_type,
                cost,
                (short) 0,
                hotel,
                city,
                (short) 0);
        try {
            tourService.createTour(tour);
            return "redirect:" + "/dashboard";
        } catch (TourServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }


    @RequestMapping(value = "/tour/edit", method = RequestMethod.GET)
    public String editTourGetPage(Model model, @RequestParam(name = "id") String id){
        try {
            Tour tour = tourService.getTourById( Integer.parseInt(id));
            model.addAttribute("tour", tour);
        } catch (TourServiceException | NumberFormatException e) {
            logger.error(e);
            return "/error";
        }
        return "tour/edit";
    }

    @RequestMapping(value = "/tour/edit", method = RequestMethod.POST)
    public String editTourPostPage(@RequestParam(name = "idtur") String idtur,
                                   @RequestParam(name = "dateStart") String dateStart,
                                   @RequestParam(name = "dateFinish") String dateFinish,
                                   @RequestParam(name = "tur_type") String tur_type,
                                   @RequestParam(name = "menu_type") String menu_type,
                                   @RequestParam(name = "cost") Double cost,
                                   @RequestParam(name = "booking") String booking,
                                   @RequestParam(name = "hotel") String hotel,
                                   @RequestParam(name = "city") String city,
                                   @RequestParam(name = "deleted") String deleted) {
        //model.("UTF-8");
        Tour tour = new Tour(
                Integer.parseInt(idtur),
                Date.valueOf(dateStart),
                Date.valueOf(dateFinish),
                tur_type,
                menu_type,
                cost,
                Short.parseShort(booking),
                hotel,
                city,
                Short.parseShort(deleted));
        try {
            tourService.updateTour(tour);
            return "redirect:" + "/dashboard";
        } catch (TourServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @RequestMapping(value = "/tour/delete", method = RequestMethod.POST)
    public String deleteTourPostPage(@RequestParam(name = "idtur") Integer idtur,
                                   @RequestParam(name = "deleted") Short deleted) {
        if(deleted == 1){
            deleted = 0;
        } else {
            deleted = 1;
        }
        Tour tour = new Tour(idtur, deleted);
        try {
            tourService.setDeleteTour(tour);
            return "redirect:" + "/dashboard";

        } catch (TourServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @RequestMapping(value = "/tour/details", method = RequestMethod.GET)
    public String detailTourGetPage(Model model, @RequestParam(name = "id") Integer id){
        try {
            Tour tour = tourService.getTourById(id);
            model.addAttribute("tourItem", tour);
        } catch (TourServiceException | NumberFormatException e) {
            logger.error(e);
            return "/error";
        }
        return "tour/details";
    }


}
