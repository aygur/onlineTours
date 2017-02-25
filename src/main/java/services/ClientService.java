package services;

import common.ClientDAOException;
import models.dao.ClientDAO;
import models.pojo.Client;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by dmitrii on 23.02.17.
 */
public class ClientService {
    static Logger logger = Logger.getLogger(ClientService.class);

    public static Client authorize(String login, String password) throws ClientDAOException {
        return ClientDAO.getClientByLoginAndPassword(login, password);
    }

    public static boolean registration(String login, String password, String email) throws ClientDAOException  {
        return ClientDAO.registrationClient(login, password, email);
    }

    public static boolean update(Client client) throws ClientDAOException {
        return ClientDAO.updateClient(client);
    }

    public static Client getClientById(int id) throws ClientDAOException {
        return ClientDAO.getClientById(id);
    }

    public static List<Client> getAllClient() throws ClientDAOException {
        return ClientDAO.getAll();
    }

    public static boolean setClientBlocked(int id) throws ClientDAOException {
        return ClientDAO.setClientBlocked(id);
    }

}
