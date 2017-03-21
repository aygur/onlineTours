package com.naraikin.onlinetours.models.dao.interfaces;

import com.naraikin.onlinetours.common.exception.TravelVoucherDAOException;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.models.pojo.TravelVoucher;

import java.util.List;

/**
 * Created by dmitrii on 08.03.17.
 */
public interface TravelVoucherDAO {

    List<TravelVoucher> getAll() throws TravelVoucherDAOException;
    List<TravelVoucher> getAllByClient(Client client) throws TravelVoucherDAOException;

    int createTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherDAOException;
    TravelVoucher getTravelVoucherById(int id) throws TravelVoucherDAOException;
    Integer deleteTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherDAOException;
    Integer updateTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherDAOException;

}
