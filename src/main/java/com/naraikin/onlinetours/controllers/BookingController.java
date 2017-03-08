package com.naraikin.onlinetours.controllers;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.common.exception.TourServiceException;
import com.naraikin.onlinetours.common.exception.TravelVoucherServiceException;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.models.pojo.Tour;
import com.naraikin.onlinetours.models.pojo.TravelVoucher;
import com.naraikin.onlinetours.models.pojo.VoucherStatus;
import com.naraikin.onlinetours.services.ClientService;
import com.naraikin.onlinetours.services.TourService;
import com.naraikin.onlinetours.services.TravelVoucherService;
import com.naraikin.onlinetours.services.VoucherStatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by dmitrii on 08.03.17.
 */
@Controller
public class BookingController {
    static Logger logger = Logger.getLogger(BookingController.class);

    private TourService tourService;
    private ClientService clientService;
    private VoucherStatusService voucherStatusService;
    private TravelVoucherService travelVoucherService;

    @Autowired
    public void setTravelVoucherService(TravelVoucherService travelVoucherService) {
        this.travelVoucherService = travelVoucherService;
    }

    @Autowired
    public void setVoucherStatusService(VoucherStatusService voucherStatusService) {
        this.voucherStatusService = voucherStatusService;
    }

    @Autowired
    public void setTourService(TourService tourService) {
        this.tourService = tourService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }


    @RequestMapping(value = "/book_before", method = RequestMethod.GET)
    public String bookingTourGetPage(Model model, @RequestParam(name = "idtur") Integer idtur) {
        try {
            Tour tour = tourService.getTourById(idtur);
            model.addAttribute("tourItem", tour);
            return "book/book_before";
        } catch (TourServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @RequestMapping(value = "/book_before", method = RequestMethod.POST)
    public String bookingTourPostPage(HttpSession session,
                                      @RequestParam(name = "idtur") Integer idtur) {
        try {
            TravelVoucher voucher = new TravelVoucher();

            Tour tour = tourService.getTourById(idtur);
            tour.setBooking((short)1);
            voucher.setTour(tour);

            Client client = clientService.getClientById((int) session.getAttribute("id"));
            voucher.setClient(client);

            VoucherStatus voucherStatus = voucherStatusService.getVoucherStatusById(1);
            voucher.setVoucherStatus(voucherStatus);

            voucher.setBooking_date(Timestamp.valueOf(LocalDateTime.now()));
            voucher.setIdtravel_voucher(travelVoucherService.createTravelVoucher(voucher));

            return "redirect:/book_after?id="+voucher.getIdtravel_voucher();
        } catch (TourServiceException |TravelVoucherServiceException | ClientServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @RequestMapping(value = "/book_after", method = RequestMethod.GET)
    public String afterTourGetPage(Model model, @RequestParam(name = "id") Integer id) {
        try {
            TravelVoucher travelVoucher = travelVoucherService.getTravelVoucherById(id);
            model.addAttribute("travelVoucher", travelVoucher);
            return "book/book_after";
        } catch (TravelVoucherServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @RequestMapping(value = "/book_cancel", method = RequestMethod.POST)
    public String cancelTourPostPage(Model model, @RequestParam(name = "id") Integer id) {
        try {
            TravelVoucher travelVoucher = travelVoucherService.getTravelVoucherById(id);
            travelVoucherService.deleteTravelVoucher(travelVoucher);
            return "redirect:/dashboard";
        } catch (TravelVoucherServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }


    @RequestMapping(value = "/bank", method = RequestMethod.GET)
    public String bankGetPage(Model model, @RequestParam(name = "id") Integer id) {
        try {
            TravelVoucher travelVoucher = travelVoucherService.getTravelVoucherById(id);
            model.addAttribute("travelVoucher", travelVoucher);
            return "book/book_after";
        } catch (TravelVoucherServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }


}
