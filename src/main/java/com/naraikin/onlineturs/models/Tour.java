package com.naraikin.onlineturs.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by dmitrii on 20.02.17.
 */

@XmlType(propOrder = {"idtur","dateStart", "dateFinish",
        "tur_type", "menu_type", "cost", "booking", "hotel",
        "city"})
@XmlRootElement
public class Tour {
    private int idtur;
    private Date dateStart;
    private Date dateFinish;
    private String tur_type;
    private String menu_type;
    private Double cost;
    private short booking;
    private String hotel;
    private String city;

    public int getIdtur() {
        return idtur;
    }

    public void setIdtur(int idtur) {
        this.idtur = idtur;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getTur_type() {
        return tur_type;
    }

    public void setTur_type(String tur_type) {
        this.tur_type = tur_type;
    }

    public String getMenu_type() {
        return menu_type;
    }

    public void setMenu_type(String menu_type) {
        this.menu_type = menu_type;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public short getBooking() {
        return booking;
    }

    public void setBooking(short booking) {
        this.booking = booking;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
