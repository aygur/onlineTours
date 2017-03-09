package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.TourDAOException;
import com.naraikin.onlinetours.models.connector.Connector;
import com.naraikin.onlinetours.models.pojo.Tour;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 25.02.17.
 */
@Repository
public class TourDAOImpl implements TourDAO {

    private static Logger logger = Logger.getLogger(TourDAOImpl.class);

    private static final String SQL_SELECT_ALL = "SELECT * FROM tour";
    private static final String SQL_DELETE_TOUR = "UPDATE tour SET deleted=? " +
            " WHERE idtur = ?";
    private static final String SQL_BOOK_TOUR = "UPDATE tour SET booking=? " +
            " WHERE idtur = ?";
    private static final String SQL_UPDATE_TOUR = "UPDATE tour SET  dateStart= ?, dateFinish = ?, " +
            " tur_type = ?, menu_type =?, cost =? , booking= ?, " +
            " hotel = ?, city=? " +
            " WHERE idtur = ?";
    private static final String SQL_INSERT_TOUR = "INSERT tour SET  dateStart= ?, dateFinish = ?, " +
            " tur_type = ?, menu_type =?, cost =? , booking= ?, " +
            " hotel = ?, city=?";
    private static final String SELECT_TOUR_BY_ID = " SELECT * FROM tour WHERE idtur=?";


    public Tour create(Tour tour) throws TourDAOException {
        try( PreparedStatement ps = Connector.getDbCon().prepareStatement(
                SQL_INSERT_TOUR)){
            ps.setDate(1,tour.getDateStart());
            ps.setDate(2, tour.getDateFinish());
            ps.setString(3,tour.getTur_type());
            ps.setString(4,tour.getMenu_type());
            ps.setDouble(5,tour.getCost());
            ps.setShort(6,tour.getBooking());
            ps.setString(7, tour.getHotel());
            ps.setString(8, tour.getCity());
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            logger.error(se);
            throw new TourDAOException();
        }
        return tour;
    }

    public void setBooking(Tour tour) throws TourDAOException {

        try( PreparedStatement ps = Connector.getDbCon().prepareStatement(
                SQL_BOOK_TOUR)) {
            ps.setInt(1, tour.getBooking());
            ps.setInt(2, tour.getIdtur());
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            logger.error(se);
            throw new TourDAOException();
        }
    }
    public void setDelete(Tour tour) throws TourDAOException {

        try( PreparedStatement ps = Connector.getDbCon().prepareStatement(
                SQL_DELETE_TOUR)) {
            ps.setInt(1, tour.getDeleted());
            ps.setInt(2, tour.getIdtur());
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            logger.error(se);
            throw new TourDAOException();
        }
    }

    public void update(Tour tour) throws TourDAOException {
        logger.trace("update tour");
        try( PreparedStatement ps = Connector.getDbCon().prepareStatement(SQL_UPDATE_TOUR)) {

            ps.setDate(1,tour.getDateStart());
            ps.setDate(2, tour.getDateFinish());
            ps.setString(3,tour.getTur_type());
            ps.setString(4,tour.getMenu_type());
            ps.setDouble(5,tour.getCost());
            ps.setShort(6,tour.getBooking());
            ps.setString(7, tour.getHotel());
            ps.setString(8, tour.getCity());
            ps.setInt(9, tour.getIdtur());

            ps.executeUpdate();
        logger.trace("updated tour");
        }
        catch (SQLException se)
        {
            logger.error(se);
            throw new TourDAOException();
        }
    }

    public List<Tour> getAll() throws TourDAOException {
        List<Tour> list = new ArrayList<>();
        try (Statement statement = Connector.getDbCon().createStatement()){
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL);

            while (rs.next()){
                Tour tour = new Tour();
                tour.setIdtur(rs.getInt("idtur"));
                tour.setDateStart(rs.getDate("dateStart"));
                tour.setDateFinish(rs.getDate("dateFinish"));
                tour.setTur_type(rs.getString("tur_type"));
                tour.setMenu_type(rs.getString("menu_type"));
                tour.setCost(rs.getDouble("cost"));
                tour.setHotel(rs.getString("hotel"));
                tour.setCity(rs.getString("city"));
                tour.setBooking(rs.getByte("booking"));
                tour.setDeleted(rs.getByte("deleted"));
                list.add(tour);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new TourDAOException();
        }
        return list;
    }

    public Tour getTourById(int id) throws TourDAOException {
        Tour tour = new Tour();
        try (PreparedStatement preparedStatement = Connector.getDbCon().prepareStatement(SELECT_TOUR_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                tour.setIdtur(rs.getInt("idtur"));
                tour.setDateStart(rs.getDate("dateStart"));
                tour.setDateFinish(rs.getDate("dateFinish"));
                tour.setTur_type(rs.getString("tur_type"));
                tour.setMenu_type(rs.getString("menu_type"));
                tour.setCost(rs.getDouble("cost"));
                tour.setHotel(rs.getString("hotel"));
                tour.setCity(rs.getString("city"));
                tour.setDeleted(rs.getByte("deleted"));
                tour.setBooking(rs.getByte("booking"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new TourDAOException();
        }
        return tour;
    }
}
