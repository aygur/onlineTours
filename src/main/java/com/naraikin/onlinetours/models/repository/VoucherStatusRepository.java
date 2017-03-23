package com.naraikin.onlinetours.models.repository;

import com.naraikin.onlinetours.models.entities.VoucherStatusE;
import com.naraikin.onlinetours.models.pojo.VoucherStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dmitrii on 21.03.17.
 */
public interface VoucherStatusRepository extends CrudRepository<VoucherStatusE, Integer> {
    List<VoucherStatusE> findAll();
    VoucherStatusE findOne(Integer idvoucher_status);
}
