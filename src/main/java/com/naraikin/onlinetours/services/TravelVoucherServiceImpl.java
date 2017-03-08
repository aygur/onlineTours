package com.naraikin.onlinetours.services;

import com.naraikin.onlinetours.common.exception.TravelVoucherDAOException;
import com.naraikin.onlinetours.common.exception.TravelVoucherServiceException;
import com.naraikin.onlinetours.models.dao.TravelVoucherDAO;
import com.naraikin.onlinetours.models.dao.TravelVoucherDAOImpl;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.models.pojo.TravelVoucher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dmitrii on 07.03.17.
 */
@Service
public class TravelVoucherServiceImpl implements TravelVoucherService {
    static Logger logger = Logger.getLogger(TravelVoucherServiceImpl.class);

    private TravelVoucherDAO travelVoucherDAO;

    @Autowired
    public void setTravelVoucherDAO(TravelVoucherDAO travelVoucherDAO) {
        this.travelVoucherDAO = travelVoucherDAO;
    }




    public List<TravelVoucher> getAll() throws TravelVoucherServiceException {
        try {
            return travelVoucherDAO.getAll();
        } catch (TravelVoucherDAOException e) {
            logger.error(e);
            throw new TravelVoucherServiceException();
        }
    }

    @Override
    public int createTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherServiceException {
        try {
            return travelVoucherDAO.createTravelVoucher(travelVoucher);
        } catch (TravelVoucherDAOException e) {
            logger.error(e);
            throw new TravelVoucherServiceException();
        }
    }


    @Override
    public TravelVoucher getTravelVoucherById(int id) throws TravelVoucherServiceException {
        try {
            return travelVoucherDAO.getTravelVoucherById(id);
        } catch (TravelVoucherDAOException e) {
            logger.error(e);
            throw new TravelVoucherServiceException();
        }
    }

    @Override
    public Integer deleteTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherServiceException{
        try {
            return travelVoucherDAO.deleteTravelVoucher(travelVoucher);
        } catch (TravelVoucherDAOException e) {
            logger.error(e);
            throw new TravelVoucherServiceException();
        }
    }

    @Override
    public List<TravelVoucher> getAllByClient(Client client)  throws TravelVoucherServiceException{
        try {
            return travelVoucherDAO.getAllByClient(client);
        } catch (TravelVoucherDAOException e) {
            logger.error(e);
            throw new TravelVoucherServiceException();
        }
    }
}
