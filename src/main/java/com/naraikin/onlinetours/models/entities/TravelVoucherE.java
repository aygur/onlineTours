package com.naraikin.onlinetours.models.entities;

import com.naraikin.onlinetours.models.pojo.Client;
import com.naraikin.onlinetours.models.pojo.Tour;
import com.naraikin.onlinetours.models.pojo.VoucherStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.Timestamp;

/**
 * Created by dmitrii on 20.02.17.
 */
@XmlType(propOrder = {"idtravel_voucher","tour", "client",
        "voucherStatus", "payment_date", "booking_date", "payment_num"})
@XmlRootElement
@Entity
@Table(name = "travel_voucher")

public class TravelVoucherE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtravel_voucher;

    @ManyToOne()
    @JoinColumn(name = "tour_id")
    private TourE tour;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private ClientE client;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    private VoucherStatusE voucherStatus;
    private Timestamp payment_date;
    private Timestamp booking_date;

    public TravelVoucherE() {
    }

    public TravelVoucherE(int idtravel_voucher, TourE tour, ClientE client, VoucherStatusE voucherStatus, Timestamp payment_date, Timestamp booking_date, String payment_num) {
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

    public ClientE getClient() {
        return client;
    }

    public void setClient(ClientE client) {
        this.client= client;
    }

    public TourE getTour() {
        return tour;
    }

    public void setTour(TourE tour) {
        this.tour = tour;
    }

    public int getIdtravel_voucher() {
        return idtravel_voucher;
    }

    public void setIdtravel_voucher(int idtravel_voucher) {
        this.idtravel_voucher = idtravel_voucher;
    }

    public VoucherStatusE getVoucherStatus() {
        return voucherStatus;
    }

    public void setVoucherStatus(VoucherStatusE voucherStatus) {
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

        TravelVoucherE that = (TravelVoucherE) o;

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

}
