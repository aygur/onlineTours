package models.dao;

import common.ClientDAOException;
import models.connector.Connector;
import models.pojo.Client;
import models.pojo.Tour;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 25.02.17.
 */
public class TourDAO {
    private static Logger logger = Logger.getLogger(TourDAO.class);
    private static final String SQL_SELECT_ALL = "SELECT * FROM tour";
    //    public Connection connection;
    private static final String SQL_DELETE_TOUR = "UPDATE from tour SET deleted=1 " +
            "where id = ?";
    private static final String SQL_UPDATE_TOUR = "UPDATE tour SET  dateStart= ?, dateFinish = ?, " +
            " tup_type = ?, menu_type =?, cost =? , booking= ?, " +
            " hotel = ?, city=? " +
            " WHERE id = ?";
    private static final String SQL_INSERT_TOUR = "INSERT tour SET  dateStart= ?, dateFinish = ?, " +
            " tup_type = ?, menu_type =?, cost =? , booking= ?, " +
            " hotel = ?, city=?";


    public static Tour create(Tour tour) throws ClientDAOException {
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

            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            // log the exception
            logger.error(se);
            throw new ClientDAOException();
        }
        return tour;
    }

    public static void setDelete(Tour tour) throws ClientDAOException {

        try( PreparedStatement ps = Connector.getDbCon().prepareStatement(
                SQL_DELETE_TOUR)) {
            // create our java preparedstatement using a sql update query
            // set the preparedstatement parameters
            ps.setInt(1, tour.getIdtur());
            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            // log the exception
            logger.error(se);
            throw new ClientDAOException();
        }
    }

    public static void update(Tour tour) throws ClientDAOException {

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

        }
        catch (SQLException se)
        {
            // log the exception
            logger.error(se);
            throw new ClientDAOException();
        }
    }


    public static List<Tour> getAll() throws ClientDAOException {
        List<Tour> list = new ArrayList<>();
        try {
            Statement statement = Connector.getDbCon().createStatement();
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
            throw new ClientDAOException();
        }
        return list;
    }

    public static Tour getTourById(int id) throws ClientDAOException {
        Tour tour = new Tour();
        try {
            Statement statement = Connector.getDbCon().createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL+" WHERE idtur="+id);

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
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ClientDAOException();
        }
        return tour;
    }
}
