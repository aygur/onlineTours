package com.naraikin.onlinetours.services;

import com.naraikin.onlinetours.common.exception.ClientDAOException;
import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.models.dao.ClientDAO;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.services.interfaces.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dmitrii on 23.02.17.
 */
@Component("ClientServiceImpl")
public class ClientServiceImpl implements ClientService {
    static Logger logger = Logger.getLogger(ClientServiceImpl.class);

    private ClientDAO clientDAO;

    @Autowired
    @Qualifier("ClientDAOImpl")
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

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
            String password = client.getPassword();
            String email = client.getEmail();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            client.setPassword(encoder.encode(password));
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
            String password = client.getPassword();
            String email = client.getEmail();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            client.setPassword(encoder.encode(password));
            return clientDAO.updateClient(client);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public Client getClientByLogin(String login) throws ClientServiceException{
        try {
            return clientDAO.getClientByLogin(login);
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

    public boolean setClientBlocked(Client client) throws ClientServiceException {
        try {
            return clientDAO.setClientBlocked(client);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

}
