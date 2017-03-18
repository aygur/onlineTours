package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.VoucherStatusDAOException;
import com.naraikin.onlinetours.models.connector.Connector;
import com.naraikin.onlinetours.models.dao.interfaces.VoucherStatusDAO;
import com.naraikin.onlinetours.models.entities.VoucherStatusE;
import com.naraikin.onlinetours.models.pojo.VoucherStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
 * Created by dmitrii on 05.03.17.
 */
@Repository("VoucherStatusDAOImplH")
public class VoucherStatusDAOImplH implements VoucherStatusDAO {

    private static Logger logger = Logger.getLogger(VoucherStatusDAOImplH.class);

    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("mydb_tours");


    @Override
    public List<VoucherStatus> getAll() throws VoucherStatusDAOException {
        List<VoucherStatus> list = new ArrayList<>();
        CriteriaBuilder builder = FACTORY.getCriteriaBuilder();
        EntityManager em = FACTORY.createEntityManager();
        CriteriaQuery<VoucherStatusE> criteria = builder.createQuery( VoucherStatusE.class );
        Root<VoucherStatusE> root = criteria.from( VoucherStatusE.class );
        criteria.select( root );
        List<VoucherStatusE> voucherStatusES = em.createQuery( criteria ).getResultList();
        em.close();
        for (VoucherStatusE voucherStatusE:
                voucherStatusES) {
            list.add(VoucherStatus.toVoucherStatus(voucherStatusE));
        }
        logger.trace("getAll ==> Hibernate");
        return list;
    }

    @Override
    public VoucherStatus getVoucherStatusById(int id) throws VoucherStatusDAOException {
        logger.trace("getTourById ==> Hibernate");
        EntityManager em = FACTORY.createEntityManager();
        VoucherStatusE voucherStatusE = em.find(VoucherStatusE.class, id);
        VoucherStatus voucherStatus = VoucherStatus.toVoucherStatus(voucherStatusE);
        em.close();
        return voucherStatus;
    }
}
