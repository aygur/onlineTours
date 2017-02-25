package services;

import common.ClientDAOException;
import models.dao.TourDAO;
import models.pojo.Tour;

import java.util.List;

/**
 * Created by dmitrii on 25.02.17.
 */
public class TourService {

    public static List<Tour> getAllTour() throws ClientDAOException {
        return TourDAO.getAll();
    }
    public static Tour getTourById(int id) throws ClientDAOException {
        return TourDAO.getTourById(id);
    }

    public static void updateTour(Tour tour) throws ClientDAOException {
        TourDAO.update(tour);
    }

    public static void createTour(Tour tour) throws ClientDAOException {
        TourDAO.create(tour);
    }

    public static void deleteTour(Tour tour) throws ClientDAOException {
        TourDAO.delete(tour);
    }

}
