package com.naraikin.onlineturs.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by dmitrii on 20.02.17.
 */
@XmlType(propOrder = {"idtravel_voucher","tour_id", "client_id",
        "status_id", "payment_date", "booking_date", "payment_num"})
@XmlRootElement
public class TravelVoucher {

    private int idtravel_voucher;
    private int tour_id;
    private int client_id;
    private int status_id;
    private Date payment_date;
    private Date booking_date;
    private String payment_num;

    public int getIdtravel_voucher() {
        return idtravel_voucher;
    }

    public void setIdtravel_voucher(int idtravel_voucher) {
        this.idtravel_voucher = idtravel_voucher;
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public String getPayment_num() {
        return payment_num;
    }

    public void setPayment_num(String payment_num) {
        this.payment_num = payment_num;
    }
}
