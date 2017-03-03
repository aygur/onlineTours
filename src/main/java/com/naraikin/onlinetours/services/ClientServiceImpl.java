package com.naraikin.onlinetours.services;

import com.naraikin.onlinetours.common.exception.ClientDAOException;
import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.models.dao.ClientDAO;
import com.naraikin.onlinetours.models.pojo.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dmitrii on 23.02.17.
 */
@Component
public class ClientServiceImpl implements ClientService {
    static Logger logger = Logger.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientDAO clientDAO;

    public Client authorize(String login, String password) throws ClientServiceException {
        try {
            return clientDAO.getClientByLoginAndPassword(login, password);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public boolean registration(Client client) throws ClientServiceException {
        try {
            return clientDAO.registrationClient(client);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public boolean registration(String login, String password, String email) throws ClientServiceException {
        try {
            return clientDAO.registrationClient(login, password, email);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public boolean update(Client client) throws ClientServiceException {
        try {
            return clientDAO.updateClient(client);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public Client getClientById(int id) throws ClientServiceException {
        try {
            return clientDAO.getClientById(id);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public List<Client> getAllClient() throws ClientServiceException {
        try {
            return clientDAO.getAll();
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public boolean setClientBlocked(int id) throws ClientServiceException {
        try {
            return clientDAO.setClientBlocked(id);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

}