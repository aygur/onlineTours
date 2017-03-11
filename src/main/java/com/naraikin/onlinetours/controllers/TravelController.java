package com.naraikin.onlinetours.controllers;

import com.naraikin.onlinetours.common.exception.TravelVoucherServiceException;
import com.naraikin.onlinetours.models.pojo.TravelVoucher;
import com.naraikin.onlinetours.services.TravelVoucherServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by dmitrii on 07.03.17.
 */
@Controller
public class TravelController {

    static Logger logger = Logger.getLogger(TravelController.class);
    TravelVoucherServiceImpl travelVoucherService;

    @Autowired
    public void setTravelVoucherService(TravelVoucherServiceImpl travelVoucherService) {
        this.travelVoucherService = travelVoucherService;
    }

    @RequestMapping(value = "/vouchers",method = RequestMethod.GET)
    public String ListVouchersGetPage(Model model) throws TravelVoucherServiceException {
            List<TravelVoucher> travelVouchers = travelVoucherService.getAll();
            model.addAttribute("travelVouchers", travelVouchers);
            return "voucher/list";
    }

    @ExceptionHandler(TravelVoucherServiceException.class)
    public ModelAndView handleTravelVoucherServiceException(
            TravelVoucherServiceException exception, HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.addObject("url", req.getRequestURL());
        logger.error(exception);
        return modelAndView;
    }

}
