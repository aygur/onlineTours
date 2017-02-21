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

    /*@Override
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
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (idclient != client.idclient) return false;
        if (lastName != null ? !lastName.equals(client.lastName) : client.lastName != null) return false;
        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        if (birthDate != null ? !birthDate.equals(client.birthDate) : client.birthDate != null) return false;
        if (doc != null ? !doc.equals(client.doc) : client.doc != null) return false;
        if (address != null ? !address.equals(client.address) : client.address != null) return false;
        return gender != null ? gender.equals(client.gender) : client.gender == null;
    }

    @Override
    public int hashCode() {
        int result = idclient;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (doc != null ? doc.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }
}
