package com.naraikin.onlinetours.controllers;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.services.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * Created by dmitrii on 07.03.17.
 */
@Controller
public class LoginLogoutController {

    static Logger logger = Logger.getLogger(LoginLogoutController.class);

    private ClientService clientService;

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGetPage(){
        return "client/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPostPage(Model model, @RequestParam(name = "login") String login,
                                @RequestParam(name = "password") String password,
                                HttpSession session){

        try {
            Client client = clientService.authorize(login, password);
            if (client.getIdclient() != 0) {
                logger.trace("authorized");
                session.setAttribute("login", login);
                session.setAttribute("id", client.getIdclient());
                return "redirect:" + "/dashboard";

            } else {
                logger.trace("not authorized " + login);
                model.addAttribute("error","Неверный логин или пароль");
                return "redirect:" + "/error";
            }
        } catch (ClientServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutGetPage(HttpSession session){
        session.setAttribute("login", null);
        session.invalidate();
        return "client/login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationGetPage(){
        return "client/registration";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPostPage(Model model,
                                       @RequestParam(name = "lastName") String lastName,
                                       @RequestParam(name = "firstName") String firstName,
                                       @RequestParam(name = "phone") String phone,
                                       @RequestParam(name = "birthDate") String birthDate,
                                       @RequestParam(name = "doc") String doc,
                                       @RequestParam(name = "address") String address,
                                       @RequestParam(name = "gender") String gender,
                                       @RequestParam(name = "login") String login,
                                       @RequestParam(name = "password") String password,
                                       @RequestParam(name = "email") String email){
        logger.trace("on post");

        Client client = new Client(0,
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
                "user",
                (short) 0);

        try {
            if(clientService.registration(client)) {
                logger.trace("registration ok");
                model.addAttribute("error", "Регистрация успешна! Войдите в систему");
                return "redirect:"+"client/login";
            }else{
                logger.trace("not registration");
                model.addAttribute("error", "Допущена ошибка при вводе данных");
                return "redirect:" + "client/registration";
            }
        } catch (ClientServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }


}
