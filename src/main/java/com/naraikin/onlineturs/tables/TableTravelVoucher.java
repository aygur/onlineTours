package com.naraikin.onlineturs.tables;

import com.naraikin.onlineturs.databasemanager.MysqlConnect;
import com.naraikin.onlineturs.models.TravelVoucher;
import com.naraikin.onlineturs.parser.Parser;
import com.naraikin.onlineturs.parser.impl.JaxbParser;
import com.naraikin.onlineturs.threads.Counter;
import com.naraikin.onlineturs.wraps.WrapTravelVoucher;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.*;

/**
 * Created by dmitrii on 20.02.17.
 */
public class TableTravelVoucher extends ParentDAO implements DAOI {

    public static final Logger logger = Logger.getLogger(TableTravelVoucher.class);
    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    private WrapTravelVoucher wrapTravelVoucher = new WrapTravelVoucher();
    private File file = new File("TravelVouchers.xml");
    private Parser parser = new JaxbParser();

    public TableTravelVoucher(){
        tableName = "travel_voucher";
    }

    @Override
    public void insertAllRowDB(Counter counter) {
        logger.trace("Начало проверки travel_voucher " );
        String sqlReq = "INSERT INTO travel_voucher "
                + "(idtravel_voucher, tour_id, client_id, status_id, " +
                "payment_date, booking_date, payment_num) VALUES"
                + "(?,?,?,?,?,?,?)";
        for(TravelVoucher travelVoucher: wrapTravelVoucher.getList()) {
            synchronized (counter) {
                logger.trace("Вход в синхр. блок для " + TableTravelVoucher.class);
                while (!counter.isExist(travelVoucher.getClient()) &&
                        !counter.isExist(travelVoucher.getTour()) &&
                        !counter.isExist(travelVoucher.getVoucherStatus())) {
                    try {
                        counter.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    logger.trace("Выход из блока " + TableTravelVoucher.class);
                }


            try {
                PreparedStatement prepStat =
                        MysqlConnect.getDbCon().prepareStatement(sqlReq);
                prepStat.setInt(1, travelVoucher.getIdtravel_voucher());
                prepStat.setInt(2, travelVoucher.getTour().getIdtur());
                prepStat.setInt(3, travelVoucher.getClient().getIdclient());
                prepStat.setInt(4, travelVoucher.getVoucherStatus().getIdvoucher_status());
                prepStat.setDate(5, new Date(travelVoucher.getPayment_date().getTime()));
                prepStat.setDate(6, new Date(travelVoucher.getBooking_date().getTime()));
                prepStat.setString(7, travelVoucher.getPayment_num());
                prepStat.executeUpdate();
                logger.trace("Ввод данных в базу " + TableTravelVoucher.class);
            } catch (SQLException e) {
                logger.error(e);
            }
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
                travelVoucher.setTour(TableTour.selectID(rs.getInt("tour_id")));
                travelVoucher.setClient(TableClient.selectID(rs.getInt("client_id")));
                travelVoucher.setVoucherStatus(TableVoucherStatus.selectID(rs.getInt("status_id")));
                travelVoucher.setPayment_date(rs.getDate("payment_date"));
                travelVoucher.setBooking_date(rs.getDate("booking_date"));
                travelVoucher.setPayment_num(rs.getString("payment_num"));
                wrapTravelVoucher.append(travelVoucher);
            }
        } catch (SQLException e) {
            logger.error(e.getStackTrace());

        }
    }

    @Override
    public void saveXML() {
        try {
            this.parser.saveObject(file, wrapTravelVoucher);
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void parseXML() {
        try {
            wrapTravelVoucher = (WrapTravelVoucher) parser.getObject(file, WrapTravelVoucher.class);
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }
}
