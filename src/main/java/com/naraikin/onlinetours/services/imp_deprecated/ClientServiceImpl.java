package com.naraikin.onlinetours.services.imp_deprecated;

import com.naraikin.onlinetours.common.exception.ClientServiceException;
import com.naraikin.onlinetours.models.entities.ClientE;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.models.repository.ClientRepository;
import com.naraikin.onlinetours.services.interfaces.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 23.02.17.
 */
//@Component("ClientServiceImpl")
@Deprecated
public class ClientServiceImpl implements ClientService {
    static Logger logger = Logger.getLogger(ClientServiceImpl.class);


    private ClientRepository clientRepository;

    //@Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /*@Qualifier("ClientDAOImplH")
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
*/

    public boolean registration(Client client) throws ClientServiceException {
            String password = client.getPassword();
            String email = client.getEmail();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            client.setPassword(encoder.encode(password));
            return clientRepository.save(Client.FromClientToClientE(client)).getLogin() == client.getLogin();
    }


    public boolean update(Client client) throws ClientServiceException {
        String password = client.getPassword();
        String email = client.getEmail();
        if(password.length() < 10){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            client.setPassword(encoder.encode(password));
        }
        return clientRepository.save(Client.FromClientToClientE(client)).getLogin() == client.getLogin();
    }

    public Client getClientByLogin(String login) throws ClientServiceException{
        return Client.toClient(clientRepository.findByLogin(login));
    }

    public Client getClientById(int id) throws ClientServiceException {
        return Client.toClient(clientRepository.findByidclient(id));
    }

    public List<Client> getAllClient() throws ClientServiceException {
        List<Client> clients = new ArrayList<>();
        for (ClientE clientE : clientRepository.findAll() ){
            clients.add(Client.toClient(clientE));
        }
        return clients;
    }

    public boolean setClientBlocked(Client client) throws ClientServiceException {
            return clientRepository.save(Client.FromClientToClientE(client)).getLogin() == client.getLogin();
    }
}
