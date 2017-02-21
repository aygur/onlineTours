package com.naraikin.onlineturs.tables;

import com.naraikin.onlineturs.databasemanager.MysqlConnect;
import com.naraikin.onlineturs.models.Hotel;
import com.naraikin.onlineturs.wraps.WrapHotels;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.*;

/**
 * Created by dmitrii on 20.02.17.
 */

public class TableHotel extends ParentDAO implements DAOI{

    private WrapHotels wrapHotels = new WrapHotels();
    private File file = new File("hotels.xml");

    @Override
    public void insertAllRowDB() {
        String sqlReq = "INSERT INTO client "
                + "(idhotel, name, stars, city, " +
                "type_allocation, price_per_night) VALUES"
                + "(?,?,?,?,?,?)";
        for(Hotel hotel: wrapHotels.getList()) {
            try {
                PreparedStatement prepStat =
                        MysqlConnect.getDbCon().prepareStatement(sqlReq);
                prepStat.setInt(1, hotel.getIdhotel());
                prepStat.setString(2, hotel.getName());
                prepStat.setInt(3, hotel.getStars());
                prepStat.setString(4, hotel.getCity());
                prepStat.setString(5, hotel.getType_allocation());
                prepStat.setDouble(6, hotel.getPrice_per_night());
                prepStat.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void selectAllRowDB() {
        String sqlReq = "SELECT * FROM hotel";
        try {
            Statement statement =
                    statement = MysqlConnect.getDbCon().createStatement();
            ResultSet rs = statement.executeQuery(sqlReq);

            while (rs.next()){
                Hotel hotel = new Hotel();
                hotel.setIdhotel(rs.getInt("idhotel"));
                hotel.setName(rs.getString("name"));
                hotel.setStars(rs.getShort("stars"));
                hotel.setCity(rs.getString("city"));
                hotel.setType_allocation(rs.getString("type_allocation"));
                hotel.setPrice_per_night(rs.getDouble("price_per_night"));
                wrapHotels.append(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveXML() throws JAXBException {

    }

    @Override
    public void parseXML() throws JAXBException {

    }
}
