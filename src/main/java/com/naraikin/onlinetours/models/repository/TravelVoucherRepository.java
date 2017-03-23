package com.naraikin.onlinetours.models.repository;

import com.naraikin.onlinetours.models.entities.ClientE;
import com.naraikin.onlinetours.models.entities.TravelVoucherE;
import com.naraikin.onlinetours.models.pojo.TravelVoucher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dmitrii on 21.03.17.
 */
@Repository("TravelVoucherRepository")
public interface TravelVoucherRepository extends CrudRepository<TravelVoucherE, Integer> {
    TravelVoucherE findOne(Integer idtravel_voucher);
    List<TravelVoucherE> findAll();
    List<TravelVoucherE> findByClient(ClientE client);
    TravelVoucherE save(TravelVoucherE travelVoucherE);
    void delete(TravelVoucherE travelVoucherE);

}
