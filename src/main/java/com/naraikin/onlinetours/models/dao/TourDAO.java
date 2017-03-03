package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.ClientDAOException;
import com.naraikin.onlinetours.common.exception.TourDAOException;
import com.naraikin.onlinetours.models.pojo.Tour;

import java.util.List;

/**
 * Created by dmitrii on 03.03.17.
 */
public interface TourDAO {

    public Tour create(Tour tour) throws TourDAOException;

    public void setDelete(Tour tour) throws TourDAOException;

    public void update(Tour tour) throws TourDAOException;

    public List<Tour> getAll() throws TourDAOException;

    public Tour getTourById(int id) throws TourDAOException;
}
