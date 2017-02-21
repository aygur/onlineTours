package com.naraikin.onlineturs.tables;

import com.naraikin.onlineturs.databasemanager.MysqlConnect;
import com.naraikin.onlineturs.models.Tour;
import com.naraikin.onlineturs.parser.Parser;
import com.naraikin.onlineturs.parser.impl.JaxbParser;
import com.naraikin.onlineturs.wraps.WrapTours;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.*;

/**
 * Created by dmitrii on 20.02.17.
 */
public class TableTour extends ParentDAO implements DAOI {

    private WrapTours wrapTours = new WrapTours();
    private File file = new File("tours.xml");
    private Parser parser = new JaxbParser();

    public TableTour() {
        tableName = "tour";
    }

    @Override
    public void insertAllRowDB() {

        String sqlReq = "INSERT INTO tour "
                + "(idtur, dateStart, dateFinish, tur_type, " +
                "menu_type, cost, booking, hotel, city) VALUES "
                + "(?,?,?,?,?,?,?,?, ?)";
        for(Tour tour: wrapTours.getList()) {
            try {
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
                prepStat.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Override
    public void saveXML() throws JAXBException {
        this.parser.saveObject(file, wrapTours);
    }

    @Override
    public void parseXML() throws JAXBException {
        wrapTours = (WrapTours) parser.getObject(file, WrapTours.class);
    }
}
