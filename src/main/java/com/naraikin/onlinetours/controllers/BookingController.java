package com.naraikin.onlinetours.controllers;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.common.exception.TourServiceException;
import com.naraikin.onlinetours.common.exception.TravelVoucherServiceException;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.models.pojo.Tour;
import com.naraikin.onlinetours.models.pojo.TravelVoucher;
import com.naraikin.onlinetours.models.pojo.VoucherStatus;
import com.naraikin.onlinetours.services.interfaces.ClientService;
import com.naraikin.onlinetours.services.interfaces.TourService;
import com.naraikin.onlinetours.services.interfaces.TravelVoucherService;
import com.naraikin.onlinetours.services.interfaces.VoucherStatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created by dmitrii on 08.03.17.
 */
@Controller
public class BookingController {
    static Logger logger = Logger.getLogger(BookingController.class);

    private Integer sum;
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

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
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

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/book_before", method = RequestMethod.POST)
    public String bookingTourPostPage(@RequestParam(name = "idtur") Integer idtur) {
        try {
            TravelVoucher voucher = new TravelVoucher();

            Tour tour = tourService.getTourById(idtur);
            if(tourService.isBookingNow(tour)){
                return "redirect:" + "/error"; //// Быстрая проверка
            }
            tour.setBooking((short)1);
            voucher.setTour(tour);

            Authentication
                    auth = SecurityContextHolder.getContext().getAuthentication();
            Client client = clientService.getClientByLogin(auth.getName());
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

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
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

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/book_cancel", method = RequestMethod.POST)
    public String cancelTourPostPage(Model model, @RequestParam(name = "id") Integer id) {
        try {
            TravelVoucher travelVoucher = travelVoucherService.getTravelVoucherById(id);
            Tour tour = travelVoucher.getTour();
            tour.setBooking((short)0);
            travelVoucher.setTour(tour);
            travelVoucherService.deleteTravelVoucher(travelVoucher);
            //model.addAttribute("errors", "Бронирование тура "+id + " отменено");
            return "redirect:/dashboard";
        } catch (TravelVoucherServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/bank", method = RequestMethod.GET)
    public String bankGetPage(Model model, @RequestParam(name = "idtur") Integer id) {
        try {
            TravelVoucher travelVoucher = travelVoucherService.getTravelVoucherById(id);
            Random random = new Random();
            Integer oneS = random.nextInt(10);
            Integer twoS = random.nextInt(10);
            this.sum = oneS + twoS;
            model.addAttribute("travelVoucher", travelVoucher);
            model.addAttribute("one", oneS);
            model.addAttribute("two", twoS);
            return "book/book_bank";
        } catch (TravelVoucherServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/bank", method = RequestMethod.POST)
    public String bankPostPage(Model model, @RequestParam(name = "idtur") Integer id,
                               @RequestParam(name = "sum_user")Integer sum_user) {
        try {
            TravelVoucher travelVoucher = travelVoucherService.getTravelVoucherById(id);
            if(this.sum == sum_user){
                travelVoucher.setPayment_date(Timestamp.valueOf(LocalDateTime.now()));
                travelVoucher.setPayment_num(sum_user.toString()+Timestamp.valueOf(LocalDateTime.now()).getTime());
                travelVoucher.setVoucherStatus(voucherStatusService.getVoucherStatusById(2));
                travelVoucherService.updateTravelVoucher(travelVoucher);
                model.addAttribute("travelVoucher", travelVoucher);
                return "redirect:/voucher?idtur="+id;
            } else {
                Random random = new Random();
                Integer oneS = random.nextInt(10);
                Integer twoS = random.nextInt(10);
                this.sum = oneS + twoS;
                model.addAttribute("one", oneS);
                model.addAttribute("two", twoS);
                model.addAttribute("errors", "Оплата не прошла, повтор");
                model.addAttribute("travelVoucher", travelVoucher);
                return "book/book_bank";
            }

        } catch (TravelVoucherServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/voucher", method = RequestMethod.GET)
    public String voucherGetPage(Model model, @RequestParam(name = "idtur") Integer id) {
        try {
            TravelVoucher travelVoucher = travelVoucherService.getTravelVoucherById(id);
            model.addAttribute("travelVoucher", travelVoucher);
            return "book/book_finish";
        } catch (TravelVoucherServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }
}
