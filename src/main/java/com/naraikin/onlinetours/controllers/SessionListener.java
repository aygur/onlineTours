package com.naraikin.onlinetours.controllers;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.common.Mailer;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.services.ClientService;
import org.apache.log4j.Logger;
import com.naraikin.onlinetours.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

/**
 * Created by dmitrii on 27.02.17.
 */
@WebListener("SessionListener")
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    static private Logger logger = Logger.getLogger(SessionListener.class);

    @Autowired
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.trace(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.trace("attribute add");
        if("id".equals(event.getName()) && event.getValue() != null) {
            Integer id = (Integer) event.getValue();
            try {
                Client client = clientService.getClientById(id);
                if(client.getRole().equals("admin")){
                    logger.trace(client.getEmail());
                    Mailer.sendEmail(client.getEmail(), "You login to OnlineTuors as 'admin' role");
                }
            } catch (ClientServiceException e) {
                logger.trace(e);
            }
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}
