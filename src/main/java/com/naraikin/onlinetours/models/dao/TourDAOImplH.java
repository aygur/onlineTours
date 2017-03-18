package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.TourDAOException;
import com.naraikin.onlinetours.models.connector.Connector;
import com.naraikin.onlinetours.models.dao.interfaces.TourDAO;
import com.naraikin.onlinetours.models.entities.TourE;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.models.pojo.Tour;
import org.apache.log4j.Logger;
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
 * Created by dmitrii on 25.02.17.
 */
@Repository("TourDAOImplH")
public class TourDAOImplH implements TourDAO {

    private static Logger logger = Logger.getLogger(TourDAOImplH.class);

    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("mydb_tours");

    public Tour create(Tour tour) throws TourDAOException {
        logger.trace("setDelete tour");
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        TourE tourE= entityManager.merge( Tour.toTourE(tour));
        logger.trace( "setDeleteTour ==> Hibernate" );
        txn.commit();
        entityManager.close();
        return Tour.FromTourEToTour(tourE);

    }

    public void setBooking(Tour tour) throws TourDAOException {
        logger.trace("setBooking tour");
        new TourDAOImpl().setBooking(tour);
    }
    public void setDelete(Tour tour) throws TourDAOException {
        logger.trace("setDelete tour");
        new TourDAOImpl().setDelete(tour);
    }

    public void update(Tour tour) throws TourDAOException {
        logger.trace("update tour");
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.merge( Tour.toTourE(tour));
        logger.trace( "updateTour ==> Hibernate" );
        txn.commit();
        entityManager.close();
    }

    public List<Tour> getAllTourForClient() throws TourDAOException{
        List<Tour> list = new ArrayList<>();
        CriteriaBuilder builder = FACTORY.getCriteriaBuilder();
        EntityManager em = FACTORY.createEntityManager();
        CriteriaQuery<TourE> criteria = builder.createQuery( TourE.class );
        Root<TourE> root = criteria.from( TourE.class );
        criteria.select( root );
        criteria.where( builder.and(
                builder.equal( root.get( "booking" ), 0 ),
                builder.equal( root.get( "deleted" ), 0 ))  );
        List<TourE> tourES = em.createQuery( criteria ).getResultList();
        em.close();
        for (TourE tourE:
                tourES) {
            list.add(Tour.FromTourEToTour(tourE));
        }
        logger.trace("getAllTourForClient ==> Hibernate");
        return list;
    }

    public List<Tour> getAll() throws TourDAOException {
        List<Tour> list = new ArrayList<>();
        CriteriaBuilder builder = FACTORY.getCriteriaBuilder();
        EntityManager em = FACTORY.createEntityManager();
        CriteriaQuery<TourE> criteria = builder.createQuery( TourE.class );
        Root<TourE> root = criteria.from( TourE.class );
        criteria.select( root );
        List<TourE> tourES = em.createQuery( criteria ).getResultList();
        em.close();
        for (TourE tourE:
                tourES) {
            list.add(Tour.FromTourEToTour(tourE));
        }
        logger.trace("getAll ==> Hibernate");
        return list;
    }

    public Tour getTourById(int id) throws TourDAOException {
        logger.trace("getTourById ==> Hibernate");
        EntityManager em = FACTORY.createEntityManager();
        TourE tourE = em.find(TourE.class, id);
        Tour tour = Tour.FromTourEToTour(tourE);
        em.close();
        return tour;
    }
}
