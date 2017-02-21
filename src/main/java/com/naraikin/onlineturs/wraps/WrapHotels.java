package com.naraikin.onlineturs.wraps;

import com.naraikin.onlineturs.models.Hotel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 20.02.17.
 */
@XmlRootElement(name = "Hotels")
public class WrapHotels implements WrapI <Hotel> {

    private List<Hotel> hotelList = new ArrayList<Hotel>();

    @Override
    public List<Hotel> getList() {
        return hotelList;
    }

    @XmlElement(name = "hotels")
    @Override
    public void setList(List<Hotel> list) {
        this.hotelList = list;

    }

    @Override
    public void append(Hotel c) {
        hotelList.add(c);

    }
}
