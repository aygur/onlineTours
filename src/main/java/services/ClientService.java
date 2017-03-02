package services;

import common.ClientDAOException;
import common.ClientServiceException;
import models.dao.ClientDAO;
import models.pojo.Client;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by dmitrii on 23.02.17.
 */
public class ClientService {
    static Logger logger = Logger.getLogger(ClientService.class);

    public static Client authorize(String login, String password) throws ClientServiceException {
        try {
            return ClientDAO.getClientByLoginAndPassword(login, password);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public static boolean registration(Client client) throws ClientServiceException {
        try {
            return ClientDAO.registrationClient(client);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public static boolean registration(String login, String password, String email) throws ClientServiceException {
        try {
            return ClientDAO.registrationClient(login, password, email);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public static boolean update(Client client) throws ClientServiceException {
        try {
            return ClientDAO.updateClient(client);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public static Client getClientById(int id) throws ClientServiceException {
        try {
            return ClientDAO.getClientById(id);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public static List<Client> getAllClient() throws ClientServiceException {
        try {
            return ClientDAO.getAll();
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

    public static boolean setClientBlocked(int id) throws ClientServiceException {
        try {
            return ClientDAO.setClientBlocked(id);
        } catch (ClientDAOException e) {
            logger.error(e);
            throw new ClientServiceException();
        }
    }

}
