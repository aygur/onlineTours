package com.naraikin.onlinetours.common.exception;

/**
 * Created by dmitrii on 07.03.17.
 */
public class TravelVoucherServiceException extends Exception {
    @Override
    public String getMessage() {
        return super.getMessage() + "TravelVoucherServiceException";
    }
}
