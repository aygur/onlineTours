package com.naraikin.onlinetours.models.pojo;

import com.naraikin.onlinetours.models.entities.VoucherStatusE;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by dmitrii on 19.02.17.
 */
@XmlType(propOrder = {"idvoucher_status","status"})
@XmlRootElement
public class VoucherStatus {
    private int idvoucher_status;
    private String status;

    public VoucherStatus() {
    }

    public VoucherStatus(int idvoucher_status, String status) {
        this.idvoucher_status = idvoucher_status;
        this.status = status;
    }

    public int getIdvoucher_status() {
        return idvoucher_status;
    }

    public void setIdvoucher_status(int idvoucher_status) {
        this.idvoucher_status = idvoucher_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoucherStatus that = (VoucherStatus) o;

        if (idvoucher_status != that.idvoucher_status) return false;
        return status.equals(that.status);
    }

    @Override
    public int hashCode() {
        int result = idvoucher_status;
        result = 31 * result + status.hashCode();
        return result;
    }

    public static VoucherStatus toVoucherStatus(VoucherStatusE voucherStatusE){
        return new VoucherStatus(voucherStatusE.getIdvoucher_status(),
                voucherStatusE.getStatus());
    }

    public static VoucherStatusE fromVoucherStatus(VoucherStatus voucherStatus){
        return new VoucherStatusE(voucherStatus.getIdvoucher_status(),
                voucherStatus.getStatus());
    }
}
