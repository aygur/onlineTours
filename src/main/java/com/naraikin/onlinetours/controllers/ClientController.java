package com.naraikin.onlinetours.controllers;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.common.exception.TravelVoucherServiceException;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.models.pojo.TravelVoucher;
import com.naraikin.onlinetours.services.interfaces.ClientService;
import com.naraikin.onlinetours.services.interfaces.TravelVoucherService;
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
import java.sql.Date;
import java.util.List;

/**
 * Created by dmitrii on 07.03.17.
 */
@Controller
public class ClientController {

    static Logger logger = Logger.getLogger(ClientController.class);

    private ClientService clientService;
    private TravelVoucherService travelVoucherService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setTravelVoucherService(TravelVoucherService travelVoucherService) {
        this.travelVoucherService = travelVoucherService;
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String listClientGetPage(Model model, HttpSession session) {
        logger.trace("GET");
        try {
            List<Client> clients = clientService.getAllClient();
            model.addAttribute("clients", clients);
            return "client/list";
        } catch (ClientServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @RequestMapping(value = "/client/details", method = RequestMethod.GET)
    public String detailClientGetPage(Model model,
                                      @RequestParam(name = "id") Integer id ) {
        logger.trace("GET");
        try {
            Client client = clientService.getClientById(id);
            List<TravelVoucher> travelVouchers = travelVoucherService.getAllByClient(client);
            model.addAttribute("client", client);
            model.addAttribute("travelVouchers", travelVouchers);
            return "client/lk";
        } catch (ClientServiceException | TravelVoucherServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @RequestMapping(value = "/client/edit", method = RequestMethod.GET)
    public String clientGetPage(Model model,
                                @RequestParam(name = "id", defaultValue = "0") Integer id) {
        logger.trace("GET");
        Client client = null;
        try {
            if (id > 0) {
                client = clientService.getClientById(id);
            } else {
                Authentication
                        auth = SecurityContextHolder.getContext().getAuthentication();
                client = clientService.getClientByLogin(auth.getName());
            }
            model.addAttribute("client", client);
            return "client/edit";
        } catch (ClientServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @RequestMapping(value = "/client/edit", method = RequestMethod.POST)
    public String clientPostPage(@RequestParam(name = "id") Integer id,
                                 @RequestParam(name = "lastName") String lastName,
                                 @RequestParam(name = "firstName") String firstName,
                                 @RequestParam(name = "phone") String phone,
                                 @RequestParam(name = "birthDate") String birthDate,
                                 @RequestParam(name = "doc") String doc,
                                 @RequestParam(name = "address") String address,
                                 @RequestParam(name = "gender") String gender,
                                 @RequestParam(name = "login") String login,
                                 @RequestParam(name = "password") String password,
                                 @RequestParam(name = "email") String email,
                                 @RequestParam(name = "role") String role) {
        logger.trace("on post");

        Client client = new Client(id,
                lastName,
                firstName,
                phone,
                Date.valueOf(birthDate),
                doc,
                address,
                gender,
                login,
                password,
                email,
                role,
                (short) 0);
        try {
            clientService.update(client);
            return "redirect:" + "/clients";
        } catch (ClientServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/client/block", method = RequestMethod.POST)
    public String clientBlockPostPage(@RequestParam(name = "id") Integer id,
                                 @RequestParam(name = "block") Short block) {
        logger.trace("on post");
        Client client = new Client();
        client.setIdclient(id);
        if (block == 0) {
            block = 1;
        } else {
            block = 0;
        }
        try {
            client.setBlocked(block);
            clientService.setClientBlocked(client);
            return "redirect:" + "/clients";
        } catch (ClientServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping(value = "/LKClient", method = RequestMethod.GET)
    public String lKClientGetPage(Model model) {
        try {
            Authentication
                    auth = SecurityContextHolder.getContext().getAuthentication();
            Client client = clientService.getClientByLogin(auth.getName());
            List<TravelVoucher> travelVouchers = travelVoucherService.getAllByClient(client);
            model.addAttribute("client", client);
            model.addAttribute("travelVouchers", travelVouchers);
            return "client/lk";
        } catch (ClientServiceException | TravelVoucherServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }

    }
}