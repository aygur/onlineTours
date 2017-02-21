package com.naraikin.onlineturs.wraps;

import com.naraikin.onlineturs.models.Client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 19.02.17.
 */
@XmlRootElement(name = "clients")
public class WrapClients implements WrapI<Client> {
    private List<Client> listClients = new ArrayList<Client>();

    @XmlElement(name = "client")
    @Override
    public void setList(List<Client> clients) {
        this.listClients = clients;
    }

    public List<Client> getList() {
        return this.listClients;
    }

    public void append(Client client){
        listClients.add(client);
    }
}
