package com.naraikin.onlineturs.tables;

import com.naraikin.onlineturs.databasemanager.MysqlConnect;
import com.naraikin.onlineturs.models.Client;
import com.naraikin.onlineturs.models.Tour;
import com.naraikin.onlineturs.parser.Parser;
import com.naraikin.onlineturs.parser.impl.JaxbParser;
import com.naraikin.onlineturs.threads.Counter;
import com.naraikin.onlineturs.wraps.WrapTours;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.*;

/**
 * Created by dmitrii on 20.02.17.
 */
public class TableTour extends ParentDAO implements DAOI {

    public static final Logger logger = Logger.getLogger(TableTour.class);
    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    private WrapTours wrapTours = new WrapTours();
    private File file = new File("tours.xml");
    private Parser parser = new JaxbParser();

    public TableTour() {
        tableName = "tour";
    }

    @Override
    public void insertAllRowDB(Counter counter) {

        String sqlReq = "INSERT INTO tour "
                + "(idtur, dateStart, dateFinish, tur_type, " +
                "menu_type, cost, booking, hotel, city) VALUES "
                + "(?,?,?,?,?,?,?,?, ?)";
        for(Tour tour: wrapTours.getList()) {
            try {
                synchronized (counter){
                PreparedStatement prepStat =
                        MysqlConnect.getDbCon().prepareStatement(sqlReq);
                prepStat.setInt(1, tour.getIdtur());
                prepStat.setDate(2,new Date(tour.getDateStart().getTime()));
                prepStat.setDate(3, new Date(tour.getDateFinish().getTime()));
                prepStat.setString(4, tour.getTur_type());
                prepStat.setString(5, tour.getMenu_type());
                prepStat.setDouble(6, tour.getCost());
                prepStat.setShort(7, tour.getBooking());
                prepStat.setString(8, tour.getHotel());
                prepStat.setString(9, tour.getCity());
                logger.trace("Insert Table Tour"+ tour.getIdtur());
                    prepStat.executeUpdate();
                    counter.append(tour);
                    counter.notifyAll();
                }

            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void selectAllRowDB() {

        String sqlReq = "SELECT * FROM tour";
        try {
            Statement statement =
                    statement = MysqlConnect.getDbCon().createStatement();
            ResultSet rs = statement.executeQuery(sqlReq);

            while (rs.next()){
                Tour tour = new Tour();
                tour.setIdtur(rs.getInt("idtur"));
                tour.setDateStart(rs.getDate("dateStart"));
                tour.setDateFinish(rs.getDate("dateFinish"));
                tour.setTur_type(rs.getString("tur_type"));
                tour.setMenu_type(rs.getString("menu_type"));
                tour.setCost(rs.getDouble("cost"));
                tour.setBooking(rs.getShort("booking"));
                tour.setHotel(rs.getString("hotel"));
                tour.setCity(rs.getString("city"));
                wrapTours.append(tour);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static Tour selectID(int id) {
        String sqlReq = "SELECT * FROM tour WHERE idtur="+id;
        Tour tour = new Tour();
        try {
            Statement statement =
                    statement = MysqlConnect.getDbCon().createStatement();
            ResultSet rs = statement.executeQuery(sqlReq);

            while (rs.next()){
                tour.setIdtur(rs.getInt("idtur"));
                tour.setDateStart(rs.getDate("dateStart"));
                tour.setDateFinish(rs.getDate("dateFinish"));
                tour.setTur_type(rs.getString("tur_type"));
                tour.setMenu_type(rs.getString("menu_type"));
                tour.setCost(rs.getDouble("cost"));
                tour.setBooking(rs.getShort("booking"));
                tour.setHotel(rs.getString("hotel"));
                tour.setCity(rs.getString("city"));
            }
            return tour;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return tour;
    }

    @Override
    public void saveXML() {
        try {
            this.parser.saveObject(file, wrapTours);
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void parseXML() {
        try {
            wrapTours = (WrapTours) parser.getObject(file, WrapTours.class);
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }
}
