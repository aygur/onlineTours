package com.naraikin.onlinetours.models.entities;

import com.naraikin.onlinetours.models.pojo.Tour;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.Date;

/**
 * Created by dmitrii on 20.02.17.
 */

@XmlType(propOrder = {"idtur","dateStart", "dateFinish",
        "tur_type", "menu_type", "cost", "booking", "hotel",
        "city", "deleted"})
@XmlRootElement
@Entity
@Table(name = "tour")
public class TourE {
    private int idtur;
    private Date dateStart;
    private Date dateFinish;
    private String tur_type;
    private String menu_type;
    private Double cost;
    private short booking;
    private String hotel;
    private String city;
    private short deleted;
    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public TourE() {
    }

    public TourE(int idtur){
        this.idtur = idtur;
    }
    public TourE(int idtur, short deleted){
        this.idtur = idtur;
        this.deleted = deleted;
    }

    public TourE(int idtur, Date dateStart, Date dateFinish,
                 String tur_type, String menu_type, Double cost,
                 short booking, String hotel, String city, short deleted) {
        this.idtur = idtur;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.tur_type = tur_type;
        this.menu_type = menu_type;
        this.cost = cost;
        this.booking = booking;
        this.hotel = hotel;
        this.city = city;
        this.deleted = deleted;
    }

    public short getDeleted() {
        return deleted;
    }

    public void setDeleted(short deleted) {
        this.deleted = deleted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourE tour = (TourE) o;

        if (idtur != tour.idtur) return false;
        if (booking != tour.booking) return false;
        if (dateStart != null ? !dateStart.equals(tour.dateStart) : tour.dateStart != null) return false;
        if (dateFinish != null ? !dateFinish.equals(tour.dateFinish) : tour.dateFinish != null) return false;
        if (tur_type != null ? !tur_type.equals(tour.tur_type) : tour.tur_type != null) return false;
        if (menu_type != null ? !menu_type.equals(tour.menu_type) : tour.menu_type != null) return false;
        if (cost != null ? !cost.equals(tour.cost) : tour.cost != null) return false;
        if (hotel != null ? !hotel.equals(tour.hotel) : tour.hotel != null) return false;
        return city != null ? city.equals(tour.city) : tour.city == null;
    }

    @Override
    public int hashCode() {
        int result = idtur;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateFinish != null ? dateFinish.hashCode() : 0);
        result = 31 * result + (tur_type != null ? tur_type.hashCode() : 0);
        result = 31 * result + (menu_type != null ? menu_type.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (int) booking;
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

}
