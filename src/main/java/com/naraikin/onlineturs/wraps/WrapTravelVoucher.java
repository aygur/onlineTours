package com.naraikin.onlineturs.wraps;

import com.naraikin.onlineturs.models.TravelVoucher;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 20.02.17.
 */
@XmlRootElement(name = "TravelVouchers")
public class WrapTravelVoucher implements WrapI<TravelVoucher> {

    private List<TravelVoucher> list = new ArrayList<TravelVoucher>();

    @Override
    public List<TravelVoucher> getList() {
        return list;
    }

    @XmlElement(name = "TravelVoucher")
    @Override
    public void setList(List<TravelVoucher> c) {
        this.list = c;
    }

    @Override
    public void append(TravelVoucher c) {
        this.list.add(c);
    }
}
