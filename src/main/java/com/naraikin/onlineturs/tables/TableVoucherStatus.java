package com.naraikin.onlineturs.tables;

import com.naraikin.onlineturs.databasemanager.MysqlConnect;
import com.naraikin.onlineturs.models.VoucherStatus;
import com.naraikin.onlineturs.parser.Parser;
import com.naraikin.onlineturs.parser.impl.JaxbParser;
import com.naraikin.onlineturs.threads.Counter;
import com.naraikin.onlineturs.wraps.WrapVoucherStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.*;

/**
 * Created by dmitrii on 19.02.17.
 */

public class TableVoucherStatus extends ParentDAO implements DAOI {

    public static final Logger logger = Logger.getLogger(TableVoucherStatus.class);
    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    private WrapVoucherStatus wrapVoucherStatus = new WrapVoucherStatus();
    private File file = new File("VoucherStatuses.xml");
    private Parser parser = new JaxbParser();

    public TableVoucherStatus() {
        tableName = "voucher_status";
    }

    @Override
    public void selectAllRowDB() {
        String sqlReq = "SELECT * FROM voucher_status";

        try {
            Statement statement =
            MysqlConnect.getDbCon().createStatement();

            ResultSet rs = statement.executeQuery(sqlReq);

            while (rs.next()){
                VoucherStatus voucherStatus = new VoucherStatus();
                voucherStatus.setIdvoucher_status(rs.getInt("idvoucher_status"));
                voucherStatus.setStatus(rs.getString("status"));
                wrapVoucherStatus.append(voucherStatus);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static VoucherStatus selectID(int id) {
        String sqlReq = "SELECT * FROM voucher_status WHERE idvoucher_status="+id;
        VoucherStatus voucherStatus = new VoucherStatus();
        try {
            Statement statement =
                    MysqlConnect.getDbCon().createStatement();

            ResultSet rs = statement.executeQuery(sqlReq);

            while (rs.next()){
                voucherStatus.setIdvoucher_status(rs.getInt("idvoucher_status"));
                voucherStatus.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return voucherStatus;

    }

    @Override
    public synchronized void insertAllRowDB(Counter counter) {

        String sqlReq = "INSERT INTO voucher_status "
                + "(idvoucher_status, status) VALUES"
                + "(?,?)";
        try {
            for(VoucherStatus voucherStatus: wrapVoucherStatus.getListVoucherStatus()) {

                PreparedStatement prepStat =
                        MysqlConnect.getDbCon().prepareStatement(sqlReq);
                prepStat.setInt(1, voucherStatus.getIdvoucher_status());
                prepStat.setString(2, voucherStatus.getStatus());

                    logger.trace("Добавление " + voucherStatus.getStatus());

                    prepStat.executeUpdate();
                    counter.append(voucherStatus);

                    logger.trace("Добавление " + voucherStatus.getStatus());


            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }


    }

    @Override
    public void saveXML() {
        try {
            this.parser.saveObject(file, wrapVoucherStatus);
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void parseXML() {
        try {
            wrapVoucherStatus = (WrapVoucherStatus) parser.getObject(file, WrapVoucherStatus.class);
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }


}
