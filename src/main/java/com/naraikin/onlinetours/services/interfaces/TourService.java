package com.naraikin.onlinetours.services.interfaces;

import com.naraikin.onlinetours.common.exception.ClientDAOException;
import com.naraikin.onlinetours.common.exception.TourServiceException;
import com.naraikin.onlinetours.models.pojo.Tour;

import java.util.List;

/**
 * Created by dmitrii on 03.03.17.
 */
public interface TourService {
    List<Tour> getAllTour() throws TourServiceException;
    Tour getTourById(int id) throws TourServiceException;
    void updateTour(Tour tour) throws TourServiceException;
    void createTour(Tour tour) throws TourServiceException;
    void setDeleteTour(Tour tour) throws TourServiceException;
    void setBooking(Tour tour) throws TourServiceException;
}
