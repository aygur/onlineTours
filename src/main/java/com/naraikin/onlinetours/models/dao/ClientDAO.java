package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.ClientDAOException;
import com.naraikin.onlinetours.models.pojo.Client;

import java.util.List;

/**
 * Created by dmitrii on 03.03.17.
 */
public interface ClientDAO {
    public boolean registrationClient(Client client) throws ClientDAOException;

    public List<Client> getAll() throws ClientDAOException;

    public boolean setClientBlocked(Client client) throws ClientDAOException;

    public Client getClientByLoginAndPassword(String login, String password) throws ClientDAOException;

    public boolean registrationClient(String login, String password, String email) throws ClientDAOException;

    public boolean updateClient(Client client) throws ClientDAOException;

    public Client getClientById(int id) throws ClientDAOException;
}
