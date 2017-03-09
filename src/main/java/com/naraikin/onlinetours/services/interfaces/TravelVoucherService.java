package com.naraikin.onlinetours.services.interfaces;

import com.naraikin.onlinetours.common.exception.TravelVoucherDAOException;
import com.naraikin.onlinetours.common.exception.TravelVoucherServiceException;
import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.models.pojo.TravelVoucher;

import java.util.List;

/**
 * Created by dmitrii on 08.03.17.
 */
public interface TravelVoucherService {
    List<TravelVoucher> getAll() throws TravelVoucherServiceException;
    int createTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherServiceException;
    TravelVoucher getTravelVoucherById(int id) throws TravelVoucherServiceException;
    Integer deleteTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherServiceException;
    List<TravelVoucher> getAllByClient(Client client)  throws TravelVoucherServiceException;
    Integer updateTravelVoucher(TravelVoucher travelVoucher) throws TravelVoucherServiceException;


    }
