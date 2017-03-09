package com.naraikin.onlinetours.services;

import com.naraikin.onlinetours.common.exception.TourDAOException;
import com.naraikin.onlinetours.common.exception.TourServiceException;
import com.naraikin.onlinetours.models.dao.TourDAO;
import com.naraikin.onlinetours.models.pojo.Tour;
import com.naraikin.onlinetours.services.interfaces.TourService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dmitrii on 25.02.17.
 */
@Service
public class TourServiceImpl implements TourService {
    private static Logger logger = Logger.getLogger(TourServiceImpl.class);

    @Autowired
    private TourDAO tourDAO;


    public void setTourDAO(TourDAO tourDAO) {
        this.tourDAO = tourDAO;
    }

    public List<Tour> getAllTour() throws TourServiceException {
        try {
            return tourDAO.getAll();
        } catch (TourDAOException e) {
            logger.error(e);
            throw new TourServiceException();
        }
    }

    public Tour getTourById(int id) throws TourServiceException {
        try {
            return tourDAO.getTourById(id);
        } catch (TourDAOException e) {
            logger.error(e);
            throw new TourServiceException();
        }
    }

    public void updateTour(Tour tour) throws TourServiceException {
        try {
            tourDAO.update(tour);
        } catch (TourDAOException e) {
            logger.error(e);
            throw new TourServiceException();
        }
    }

    public void createTour(Tour tour) throws TourServiceException {
        try {
            tourDAO.create(tour);
        } catch (TourDAOException e) {
            logger.error(e);
            throw new TourServiceException();
        }
    }

    public void setDeleteTour(Tour tour) throws TourServiceException {
        try {
            tourDAO.setDelete(tour);
        } catch (TourDAOException e) {
            logger.error(e);
            throw new TourServiceException();
        }
    }

    public void setBooking(Tour tour) throws TourServiceException{
        try {
            tourDAO.setDelete(tour);
        } catch (TourDAOException e) {
            logger.error(e);
            throw new TourServiceException();
        }
    }
}
