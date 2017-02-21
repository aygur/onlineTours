package com.naraikin.onlineturs.wraps;

import com.naraikin.onlineturs.models.Tour;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrii on 20.02.17.
 */
@XmlRootElement(name = "Tours")
public class WrapTours implements WrapI<Tour> {

    List<Tour> list = new ArrayList<Tour>();

    @Override
    public List<Tour> getList() {
        return list;
    }

    @XmlElement(name = "Tour")
    @Override
    public void setList(List<Tour> c) {
        this.list = c;
    }

    @Override
    public void append(Tour c) {
        this.list.add(c);
    }
}
