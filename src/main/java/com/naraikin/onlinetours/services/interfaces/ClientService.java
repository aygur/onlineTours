package com.naraikin.onlinetours.services.interfaces;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.models.pojo.Client;

import java.util.List;

/**
 * Created by dmitrii on 03.03.17.
 */
public interface ClientService {

    boolean registration(Client client) throws ClientServiceException;

    boolean update(Client client) throws ClientServiceException;

    Client getClientById(int id) throws ClientServiceException;

    List<Client> getAllClient() throws ClientServiceException;

    boolean setClientBlocked(Client client) throws ClientServiceException;

    Client getClientByLogin(String login) throws ClientServiceException;
}
