package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.VoucherStatusDAOException;
import com.naraikin.onlinetours.models.connector.Connector;
import com.naraikin.onlinetours.models.dao.interfaces.VoucherStatusDAO;
import com.naraikin.onlinetours.models.pojo.VoucherStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 05.03.17.
 */
@Repository("VoucherStatusDAOImpl")
public class VoucherStatusDAOImpl implements VoucherStatusDAO {

    private static Logger logger = Logger.getLogger(VoucherStatusDAOImpl.class);

    private static final String SQL_SELECT_ALL = "SELECT * FROM voucher_status";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM voucher_status " +
            " WHERE idvoucher_status = ? ";


    @Override
    public List<VoucherStatus> getAll() throws VoucherStatusDAOException {
        List<VoucherStatus> list = new ArrayList<>();
        try {
            Statement statement = Connector.getDbCon().createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL);

            while (rs.next()){
                VoucherStatus voucherStatus = new VoucherStatus();
                voucherStatus.setIdvoucher_status(rs.getInt("idvoucher_status"));
                voucherStatus.setStatus(rs.getString("status"));
                list.add(voucherStatus);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new VoucherStatusDAOException();
        }
        return list;
    }

    @Override
    public VoucherStatus getVoucherStatusById(int id) throws VoucherStatusDAOException {
        try (PreparedStatement preparedStatement
                     = Connector.getDbCon().prepareStatement(SQL_SELECT_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            VoucherStatus voucherStatus = new VoucherStatus();

            while (rs.next()){
                voucherStatus.setIdvoucher_status(rs.getInt("idvoucher_status"));
                voucherStatus.setStatus(rs.getString("status"));
            }
            return voucherStatus;
        } catch (SQLException e) {
            logger.error(e);
            throw new VoucherStatusDAOException();
        }
    }
}
