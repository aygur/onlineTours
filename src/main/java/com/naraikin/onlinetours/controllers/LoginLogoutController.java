package com.naraikin.onlinetours.controllers;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.services.interfaces.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ModelAndView loginGetPage(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("clientJSP", new Client());
        mav.setViewName("client/login");
        return mav;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPostPage(@ModelAttribute("clientJSP") Client clientOut,
                                Model model, HttpSession session){
        try {
            Client client = clientService.authorize(clientOut.getLogin(), clientOut.getPassword());
            logger.trace(clientOut.getLogin()+" "+clientOut.getPassword());
            if (client.getIdclient() != 0) {
                logger.trace("authorized");
                session.setAttribute("login", client.getLogin());
                session.setAttribute("id", client.getIdclient());
                session.setAttribute("role", client.getRole());
                return "redirect:" + "/dashboard";

            } else {
                logger.trace("not authorized " + clientOut.getLogin());
                model.addAttribute("error", "Неверный логин или пароль");
                return "redirect:" + "/error";
            }
        } catch (ClientServiceException e) {
            logger.error(e);
            return "redirect:" + "/error";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutGetPage(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        session.setAttribute("login", null);
        session.invalidate();
        Authentication
                auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
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
                "ROLE_USER",
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
