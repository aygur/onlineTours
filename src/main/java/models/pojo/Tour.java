package models.pojo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by dmitrii on 20.02.17.
 */

@XmlType(propOrder = {"idtur","dateStart", "dateFinish",
        "tur_type", "menu_type", "cost", "booking", "hotel",
        "city", "deleted"})
@XmlRootElement
public class Tour {
    private int idtur;
    private java.sql.Date dateStart;
    private java.sql.Date dateFinish;
    private String tur_type;
    private String menu_type;
    private Double cost;
    private short booking;
    private String hotel;
    private String city;
    private short deleted;


    public short getDeleted() {
        return deleted;
    }

    public void setDeleted(short deleted) {
        this.deleted = deleted;
    }

    public int getIdtur() {
        return idtur;
    }

    public void setIdtur(int idtur) {
        this.idtur = idtur;
    }

    public java.sql.Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(java.sql.Date dateStart) {
        this.dateStart = dateStart;
    }

    public java.sql.Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(java.sql.Date dateFinish) {
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

        Tour tour = (Tour) o;

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
