package com.naraikin.onlinetours.services;

import com.naraikin.onlinetours.common.exception.VoucherStatusDAOException;
import com.naraikin.onlinetours.models.pojo.VoucherStatus;

/**
 * Created by dmitrii on 08.03.17.
 */
public interface VoucherStatusService {

    public VoucherStatus getVoucherStatusById(int id);
}
