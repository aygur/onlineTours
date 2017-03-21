package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.ClientDAOException;
import com.naraikin.onlinetours.models.connector.Connector;
import com.naraikin.onlinetours.models.dao.interfaces.ClientDAO;
import com.naraikin.onlinetours.models.entities.ClientE;
import com.naraikin.onlinetours.models.pojo.Client;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 23.02.17.
 */
@Repository("ClientDAOImplH")
public class ClientDAOImplH implements ClientDAO {
    private static Logger logger = Logger.getLogger(ClientDAOImplH.class);

    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("mydb_tours");

    public boolean registrationClient(Client client) throws ClientDAOException {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.persist( Client.FromClientToClientE(client ));
        logger.trace( "Entity is in persisted state ==>H" );
        txn.commit();
        return true;
    }

    public List<Client> getAll() throws ClientDAOException {
        List<Client> list = new ArrayList<>();
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ClientE> criteria = builder.createQuery( ClientE.class );
        Root<ClientE> root = criteria.from( ClientE.class );
        criteria.select( root );
        List<ClientE> clientES = em.createQuery( criteria ).getResultList();
        em.close();
        for (ClientE clientE:
             clientES) {
            list.add(Client.toClient(clientE));
        }
        logger.trace("getAll ==> Hibernate");
        return list;
    }

    public boolean setClientBlocked(Client client) throws ClientDAOException {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        ClientE clientE = entityManager.find(ClientE.class, client.getIdclient());
        clientE.setBlocked(client.getBlocked());
        entityManager.merge( clientE );
        logger.trace( "setClientBlocked ==> Hibernate" );
        txn.commit();
        return true;
    }
//vietnam0516$

    public boolean updateClient(Client client) throws ClientDAOException{
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.merge( Client.FromClientToClientE(client ));
        logger.trace( "updateClient ==> Hibernate" );
        txn.commit();
        return true;
    }

    public Client getClientById(int id) throws ClientDAOException {
        logger.trace("getClientById ==> Hibernate");
        EntityManager em = FACTORY.createEntityManager();
        ClientE clientE = em.find(ClientE.class, id);
        Client client = Client.toClient(clientE);
        em.close();
        return client;
    }

    public Client getClientByLogin(String login) throws ClientDAOException{
        logger.trace("getClientByLogin ==> Hibernate");
        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ClientE> criteria = builder.createQuery( ClientE.class );
        Root<ClientE> root = criteria.from( ClientE.class );
        criteria.select( root );
        criteria.where( builder.equal( root.get( "login" ), login ) );
        ClientE clientE = em.createQuery( criteria ).getSingleResult();
        Client client = Client.toClient(clientE);
        em.close();
        return client;
    }
}
