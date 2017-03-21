package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.ClientDAOException;
import com.naraikin.onlinetours.common.exception.TourDAOException;
import com.naraikin.onlinetours.common.exception.TravelVoucherDAOException;
import com.naraikin.onlinetours.common.exception.VoucherStatusDAOException;
import com.naraikin.onlinetours.models.connector.Connector;
import com.naraikin.onlinetours.models.dao.interfaces.ClientDAO;
import com.naraikin.onlinetours.models.dao.interfaces.TourDAO;
import com.naraikin.onlinetours.models.dao.interfaces.TravelVoucherDAO;
import com.naraikin.onlinetours.models.dao.interfaces.VoucherStatusDAO;
import com.naraikin.onlinetours.models.entities.ClientE;
import com.naraikin.onlinetours.models.entities.TravelVoucherE;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.models.pojo.Tour;
import com.naraikin.onlinetours.models.pojo.TravelVoucher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 07.03.17.
 */
@Repository("TravelVoucherDAOImplH")
public class TravelVoucherDAOImplH implements TravelVoucherDAO {

    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("mydb_tours");
    private TourDAO tourDAO;
    private ClientDAO clientDAO;
    private VoucherStatusDAO voucherStatusDAO;

    @Autowired
    @Qualifier("TourDAOImplH")
    public void setTourDAO(TourDAO tourDAO) {
        this.tourDAO = tourDAO;
    }

    @Autowired
    @Qualifier("ClientDAOImplH")
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Autowired
    @Qualifier("VoucherStatusDAOImplH")
    public void setVoucherStatusDAO(VoucherStatusDAO voucherStatusDAO) {
        this.voucherStatusDAO = voucherStatusDAO;
    }

    private static Logger logger = Logger.getLogger(TravelVoucherDAOImplH.class);

    public List<TravelVoucher> getAllByClient(Client client)  throws TravelVoucherDAOException{
        ClientE clientE = Client.FromClientToClientE(client);
        List<TravelVoucher> travelVouchers = new ArrayList<>();
        CriteriaBuilder builder = FACTORY.getCriteriaBuilder();
        EntityManager em = FACTORY.createEntityManager();
        CriteriaQuery<TravelVoucherE> criteria = builder.createQuery( TravelVoucherE.class );
        Root<TravelVoucherE> root = criteria.from( TravelVoucherE.class );
        criteria.select( root );
        criteria.where( builder.equal( root.get( "client" ), clientE ));
        List<TravelVoucherE> travelVoucherES = em.createQuery( criteria ).getResultList();
        em.close();
        for (TravelVoucherE travelVoucherE:
                travelVoucherES) {
            travelVouchers.add(TravelVoucher.returnTravelVoucher(travelVoucherE));
        }
        logger.trace("getAllByClient TravelVoucher ==> Hibernate");
        return travelVouchers;
    }


    public List<TravelVoucher> getAll() throws TravelVoucherDAOException {
        List<TravelVoucher> travelVouchers = new ArrayList<>();
        CriteriaBuilder builder = FACTORY.getCriteriaBuilder();
        EntityManager em = FACTORY.createEntityManager();
        CriteriaQuery<TravelVoucherE> criteria = builder.createQuery( TravelVoucherE.class );
        Root<TravelVoucherE> root = criteria.from( TravelVoucherE.class );
        criteria.select( root );
        List<TravelVoucherE> travelVoucherES = em.createQuery( criteria ).getResultList();
        em.close();
        for (TravelVoucherE travelVoucherE:
                travelVoucherES) {
            travelVouchers.add(TravelVoucher.returnTravelVoucher(travelVoucherE));
        }
        logger.trace("getAll TravelVoucher ==> Hibernate");
        return travelVouchers;
    }

    public int createTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherDAOException{
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        TravelVoucherE travelVoucherE = TravelVoucher.returnTravelVoucherE(travelVoucher);
        txn.begin();
        entityManager.persist( travelVoucherE);
        entityManager.flush();
        entityManager.merge(travelVoucherE.getTour());
        logger.trace( "deleteTravelVoucher ==> Hibernate" );
        txn.commit();
        entityManager.close();
        return travelVoucherE.getIdtravel_voucher();
    }

    @Override
    public TravelVoucher getTravelVoucherById(int id) throws TravelVoucherDAOException {
        logger.trace("getTravelVoucherById ==> Hibernate");
        EntityManager em = FACTORY.createEntityManager();
        TravelVoucherE travelVoucherE = em.find(TravelVoucherE.class, id);
        TravelVoucher travelVoucher = TravelVoucher.returnTravelVoucher(travelVoucherE);
        em.close();
        return travelVoucher;
    }

    @Override
    public Integer deleteTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherDAOException {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        TravelVoucherE travelVoucherE = entityManager.find(TravelVoucherE.class,travelVoucher.getIdtravel_voucher());
        txn.begin();
        entityManager.remove( travelVoucherE);
        entityManager.merge(Tour.toTourE(travelVoucher.getTour()));
        logger.trace( "deleteTravelVoucher ==> Hibernate" );
        txn.commit();
        entityManager.close();
        return 0;
    }

    @Override
    public Integer updateTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherDAOException {
        logger.trace("update tour");
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        int id_v = entityManager.merge( TravelVoucher.returnTravelVoucherE(travelVoucher)).getIdtravel_voucher();
        logger.trace( "updateTour ==> Hibernate" );
        txn.commit();
        entityManager.close();
        return id_v;
    }


}
