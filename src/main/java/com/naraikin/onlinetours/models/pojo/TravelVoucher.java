package com.naraikin.onlinetours.models.pojo;

import com.naraikin.onlinetours.models.entities.TravelVoucherE;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by dmitrii on 20.02.17.
 */
@XmlType(propOrder = {"idtravel_voucher","tour", "client",
        "voucherStatus", "payment_date", "booking_date", "payment_num"})
@XmlRootElement
public class TravelVoucher {

    private int idtravel_voucher;
    private Tour tour;
    private Client client;
    private VoucherStatus voucherStatus;
    private Timestamp payment_date;
    private Timestamp booking_date;

    public TravelVoucher() {
    }

    public TravelVoucher(int idtravel_voucher, Tour tour, Client client, VoucherStatus voucherStatus, Timestamp payment_date, Timestamp booking_date, String payment_num) {
        this.idtravel_voucher = idtravel_voucher;
        this.tour = tour;
        this.client = client;
        this.voucherStatus = voucherStatus;
        this.payment_date = payment_date;
        this.booking_date = booking_date;
        this.payment_num = payment_num;
    }

    public Timestamp getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Timestamp payment_date) {
        this.payment_date = payment_date;
    }

    public Timestamp getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Timestamp booking_date) {
        this.booking_date = booking_date;
    }

    private String payment_num;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client= client;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getIdtravel_voucher() {
        return idtravel_voucher;
    }

    public void setIdtravel_voucher(int idtravel_voucher) {
        this.idtravel_voucher = idtravel_voucher;
    }

    public VoucherStatus getVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(VoucherStatus voucherStatus) {
        this.voucherStatus = voucherStatus;
    }

    public String getPayment_num() {
        return payment_num;
    }

    public void setPayment_num(String payment_num) {
        this.payment_num = payment_num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TravelVoucher that = (TravelVoucher) o;

        if (idtravel_voucher != that.idtravel_voucher) return false;
        if (tour != null ? !tour.equals(that.tour) : that.tour != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (voucherStatus != null ? !voucherStatus.equals(that.voucherStatus) : that.voucherStatus != null)
            return false;
        if (payment_date != null ? !payment_date.equals(that.payment_date) : that.payment_date != null) return false;
        if (booking_date != null ? !booking_date.equals(that.booking_date) : that.booking_date != null) return false;
        return payment_num != null ? payment_num.equals(that.payment_num) : that.payment_num == null;
    }

    @Override
    public int hashCode() {
        int result = idtravel_voucher;
        result = 31 * result + (tour != null ? tour.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (voucherStatus != null ? voucherStatus.hashCode() : 0);
        result = 31 * result + (payment_date != null ? payment_date.hashCode() : 0);
        result = 31 * result + (booking_date != null ? booking_date.hashCode() : 0);
        result = 31 * result + (payment_num != null ? payment_num.hashCode() : 0);
        return result;
    }

    public static TravelVoucher returnTravelVoucher(TravelVoucherE travelVoucherE){
        //int idtravel_voucher, Tour tour, Client client, VoucherStatus voucherStatus,
        // Timestamp payment_date, Timestamp booking_date, String payment_num) {
        return new TravelVoucher(
                travelVoucherE.getIdtravel_voucher(),
                Tour.FromTourEToTour(travelVoucherE.getTour()),
                Client.toClient(travelVoucherE.getClient()),
                VoucherStatus.toVoucherStatus(travelVoucherE.getVoucherStatus()),
                travelVoucherE.getPayment_date(),
                travelVoucherE.getBooking_date(),
                travelVoucherE.getPayment_num());
    }

    public static TravelVoucherE returnTravelVoucherE(TravelVoucher travelVoucher){
        //int idtravel_voucher, Tour tour, Client client, VoucherStatus voucherStatus,
        // Timestamp payment_date, Timestamp booking_date, String payment_num) {
        return new TravelVoucherE(
                travelVoucher.getIdtravel_voucher(),
                Tour.toTourE(travelVoucher.getTour()),
                Client.FromClientToClientE(travelVoucher.getClient()),
                VoucherStatus.fromVoucherStatus(travelVoucher.getVoucherStatus()),
                travelVoucher.getPayment_date(),
                travelVoucher.getBooking_date(),
                travelVoucher.getPayment_num());
    }

}
