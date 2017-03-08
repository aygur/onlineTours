package com.naraikin.onlinetours.models.dao;

import com.naraikin.onlinetours.common.exception.VoucherStatusDAOException;
import com.naraikin.onlinetours.models.pojo.VoucherStatus;

import java.util.List;

/**
 * Created by dmitrii on 25.02.17.
 */
public interface VoucherStatusDAO {
   // public VoucherStatus create(VoucherStatus tour) throws VoucherStatusDAOException;

    public List<VoucherStatus> getAll() throws VoucherStatusDAOException;

    public VoucherStatus getVoucherStatusById(int id) throws VoucherStatusDAOException;
}
