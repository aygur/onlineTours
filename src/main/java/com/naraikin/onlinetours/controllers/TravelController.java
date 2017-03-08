package com.naraikin.onlinetours.controllers;

import com.naraikin.onlinetours.common.exception.TravelVoucherServiceException;
import com.naraikin.onlinetours.models.pojo.TravelVoucher;
import com.naraikin.onlinetours.services.TravelVoucherServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String ListVouchersGetPage(Model model){
        try {
            List<TravelVoucher> travelVouchers = travelVoucherService.getAll();
            model.addAttribute("travelVouchers", travelVouchers);
            return "voucher/list";
        } catch (TravelVoucherServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }

    }

}
