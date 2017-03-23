package com.naraikin.onlinetours.models.repository;

import com.naraikin.onlinetours.models.entities.ClientE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by dmitrii on 21.03.17.
 */
public interface ClientRepository extends CrudRepository<ClientE, Integer> {
    ClientE findByLogin(String login);
    ClientE findByLoginAndPassword(String login, String password);
    List<ClientE> findAll();
    ClientE findByidclient(Integer idclient);
    ClientE save(ClientE user);
}
