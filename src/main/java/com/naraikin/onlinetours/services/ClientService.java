package com.naraikin.onlinetours.services;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.models.pojo.Client;

import java.util.List;

/**
 * Created by dmitrii on 03.03.17.
 */
public interface ClientService {
    public Client authorize(String login, String password) throws ClientServiceException;

    public boolean registration(Client client) throws ClientServiceException;

    public boolean registration(String login, String password, String email) throws ClientServiceException;

    public boolean update(Client client) throws ClientServiceException;

    public Client getClientById(int id) throws ClientServiceException;

    public List<Client> getAllClient() throws ClientServiceException;

    public boolean setClientBlocked(Client client) throws ClientServiceException;
}
