package com.naraikin.onlinetours.services;

/**
 * Created by dmitrii on 07.03.17.
 */

import com.naraikin.onlinetours.common.exception.VoucherStatusDAOException;
import com.naraikin.onlinetours.models.dao.VoucherStatusDAO;
import com.naraikin.onlinetours.models.pojo.VoucherStatus;
import com.naraikin.onlinetours.services.interfaces.VoucherStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoucherStatusServiceImpl implements VoucherStatusService {

    private VoucherStatusDAO voucherStatusDAO;
    @Autowired
    public void setVoucherStatusDAO(VoucherStatusDAO voucherStatusDAO) {
        this.voucherStatusDAO = voucherStatusDAO;
    }

    public VoucherStatus getVoucherStatusById(int id) {
        try {
            return voucherStatusDAO.getVoucherStatusById(id);
        } catch (VoucherStatusDAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
