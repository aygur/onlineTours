package com.naraikin.onlineturs.tables;

import com.naraikin.onlineturs.databasemanager.MysqlConnect;
import com.naraikin.onlineturs.models.TravelVoucher;
import com.naraikin.onlineturs.parser.Parser;
import com.naraikin.onlineturs.parser.impl.JaxbParser;
import com.naraikin.onlineturs.wraps.WrapTravelVoucher;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.*;

/**
 * Created by dmitrii on 20.02.17.
 */
public class TableTravelVoucher extends ParentDAO implements DAOI {



    private WrapTravelVoucher wrapTravelVoucher = new WrapTravelVoucher();
    private File file = new File("TravelVouchers.xml");
    private Parser parser = new JaxbParser();

    public TableTravelVoucher(){
        tableName = "travel_voucher";
    }

    @Override
    public void insertAllRowDB() {
        String sqlReq = "INSERT INTO travel_voucher "
                + "(idtravel_voucher, tour_id, client_id, status_id, " +
                "payment_date, booking_date, payment_num) VALUES"
                + "(?,?,?,?,?,?,?)";
        for(TravelVoucher travelVoucher: wrapTravelVoucher.getList()) {
            try {
                PreparedStatement prepStat =
                        MysqlConnect.getDbCon().prepareStatement(sqlReq);
                prepStat.setInt(1, travelVoucher.getIdtravel_voucher());
                prepStat.setInt(2, travelVoucher.getTour_id());
                prepStat.setInt(3, travelVoucher.getClient_id());
                prepStat.setInt(4, travelVoucher.getStatus_id());
                prepStat.setDate(5, new Date(travelVoucher.getPayment_date().getTime()));
                prepStat.setDate(6, new Date(travelVoucher.getBooking_date().getTime()));
                prepStat.setString(7, travelVoucher.getPayment_num());
                prepStat.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void selectAllRowDB() {
        String sqlReq = "SELECT * FROM travel_voucher";
        try {
            Statement statement =
                    statement = MysqlConnect.getDbCon().createStatement();
            ResultSet rs = statement.executeQuery(sqlReq);

            while (rs.next()){
                TravelVoucher travelVoucher = new TravelVoucher();
                travelVoucher.setIdtravel_voucher(rs.getInt("idtravel_voucher"));
                travelVoucher.setTour_id(rs.getInt("tour_id"));
                travelVoucher.setClient_id(rs.getInt("client_id"));
                travelVoucher.setStatus_id(rs.getInt("status_id"));
                travelVoucher.setPayment_date(rs.getDate("payment_date"));
                travelVoucher.setBooking_date(rs.getDate("booking_date"));
                travelVoucher.setPayment_num(rs.getString("payment_num"));
                wrapTravelVoucher.append(travelVoucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveXML() throws JAXBException {
        this.parser.saveObject(file, wrapTravelVoucher);
    }

    @Override
    public void parseXML() throws JAXBException {
        wrapTravelVoucher = (WrapTravelVoucher) parser.getObject(file, WrapTravelVoucher.class);
    }
}
