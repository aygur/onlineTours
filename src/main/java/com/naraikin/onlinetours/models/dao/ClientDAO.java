package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.ClientDAOException;
import com.naraikin.onlinetours.models.pojo.Client;

import java.util.List;

/**
 * Created by dmitrii on 03.03.17.
 */
public interface ClientDAO {

    boolean registrationClient(Client client) throws ClientDAOException;

    List<Client> getAll() throws ClientDAOException;

    boolean setClientBlocked(Client client) throws ClientDAOException;

    Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException;

    boolean registrationClient(String login, String password, String email) throws ClientDAOException;

    boolean updateClient(Client client) throws ClientDAOException;

    Client getClientById(int id) throws ClientDAOException;
    Client getClientByLogin(String login) throws ClientDAOException;
}
