package com.naraikin.onlinetours.services;

/**
 * Created by dmitrii on 07.03.17.
 */

import com.naraikin.onlinetours.models.pojo.VoucherStatus;
import com.naraikin.onlinetours.models.repository.VoucherStatusRepository;
import com.naraikin.onlinetours.services.interfaces.VoucherStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class VoucherStatusServiceImpl implements VoucherStatusService {

    private VoucherStatusRepository voucherStatusDAO;

    @Autowired
    public void setVoucherStatusDAO(VoucherStatusRepository voucherStatusDAO) {
        this.voucherStatusDAO = voucherStatusDAO;
    }

    public VoucherStatus getVoucherStatusById(int id) {
        return VoucherStatus.toVoucherStatus(voucherStatusDAO.findOne(id));
    }
}
