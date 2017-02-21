package com.naraikin.onlineturs.models;

import javax.xml.bind.annotation.*;

import java.util.Date;

/**
 * Created by dmitrii on 18.02.17.
 */

@XmlType(propOrder = {"idclient","lastName", "firstName",
        "phone", "birthDate", "address", "gender",
        "doc"}, name = "client")
@XmlRootElement
public class Client {
    private int idclient;
    private String lastName;
    private String firstName;
    private String phone;
    private Date birthDate;
    private String doc;
    private String address;
    private String gender;


    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public Client() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Idclient: " + this.idclient
                + "\nFirst Name: " + this.firstName
                + "\nLast Name: " + this.lastName
                + "\nPhone: " + this.phone
                + "\nBirthDate: " + this.birthDate
                + "\nAddress: " + this.address
                + "\nDocument: " + this.doc
                + "\nGender: " + this.gender;
    }
}
