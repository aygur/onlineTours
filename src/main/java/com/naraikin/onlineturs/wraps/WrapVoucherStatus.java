package com.naraikin.onlineturs.wraps;

import com.naraikin.onlineturs.models.VoucherStatus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 19.02.17.
 */
@XmlRootElement(name = "VoucherStatuses")
public class WrapVoucherStatus {

    private List<VoucherStatus> listVoucherStatus = new ArrayList<VoucherStatus>();

    public List<VoucherStatus> getListVoucherStatus() {
        return listVoucherStatus;
    }

    @XmlElement(name = "VoucherStatus")
    public void setListVoucherStatus(List<VoucherStatus> listVoucherStatus) {
        this.listVoucherStatus = listVoucherStatus;
    }

    public void append(VoucherStatus voucherStatus){
        listVoucherStatus.add(voucherStatus);
    }
}
