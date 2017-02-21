package com.naraikin.onlineturs.models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dmitrii on 20.02.17.
 */
@XmlRootElement(name = "Hotel")
public class Hotel {
    private int idhotel;
    private String name;
    private short stars;
    private String city;
    private String type_allocation;
    private Double price_per_night;

    public int getIdhotel() {
        return idhotel;
    }

    public void setIdhotel(int idhotel) {
        this.idhotel = idhotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getStars() {
        return stars;
    }

    public void setStars(short stars) {
        this.stars = stars;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType_allocation() {
        return type_allocation;
    }

    public void setType_allocation(String type_allocation) {
        this.type_allocation = type_allocation;
    }

    public Double getPrice_per_night() {
        return price_per_night;
    }

    public void setPrice_per_night(Double price_per_night) {
        this.price_per_night = price_per_night;
    }
}
