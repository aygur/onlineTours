package com.naraikin.onlineturs.tables;

import com.naraikin.onlineturs.databasemanager.MysqlConnect;
import com.naraikin.onlineturs.models.VoucherStatus;
import com.naraikin.onlineturs.parser.Parser;
import com.naraikin.onlineturs.parser.impl.JaxbParser;
import com.naraikin.onlineturs.wraps.WrapVoucherStatus;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.*;

/**
 * Created by dmitrii on 19.02.17.
 */

public class TableVoucherStatus extends ParentDAO implements DAOI {

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
            e.printStackTrace();
        }

    }

    @Override
    public void insertAllRowDB() {

        String sqlReq = "INSERT INTO voucher_status "
                + "(idvoucher_status, status) VALUES"
                + "(?,?)";
        try {
            for(VoucherStatus voucherStatus: wrapVoucherStatus.getListVoucherStatus()) {
                PreparedStatement prepStat =
                        MysqlConnect.getDbCon().prepareStatement(sqlReq);
                prepStat.setInt(1, voucherStatus.getIdvoucher_status());
                prepStat.setString(2, voucherStatus.getStatus());
                prepStat.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void saveXML() throws JAXBException {
        this.parser.saveObject(file, wrapVoucherStatus);
    }

    @Override
    public void parseXML() throws JAXBException {
        wrapVoucherStatus = (WrapVoucherStatus) parser.getObject(file, WrapVoucherStatus.class);
    }


}
