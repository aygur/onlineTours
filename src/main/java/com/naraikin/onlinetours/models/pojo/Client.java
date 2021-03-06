package com.naraikin.onlinetours.models.pojo;

import com.naraikin.onlinetours.models.entities.ClientE;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by dmitrii on 18.02.17.
 */

@XmlType(propOrder = {"idclient","lastName", "firstName",
        "phone", "birthDate", "address", "gender",
        "doc", "login", "password", "email", "role", "blocked"}, name = "client")
@XmlRootElement
public class Client {
    private int idclient;
    private String lastName;
    private String firstName;
    private String phone;
    private java.sql.Date birthDate;
    private String doc;
    private String address;
    private String gender;
    private String login;
    private String password;
    private String email;
    private String role;
    private short blocked;

    public Client() {
    }

    public Client(int idclient, String lastName,
                  String firstName, String phone,
                  java.sql.Date birthDate, String doc,
                  String address, String gender, String login,
                  String password, String email, String role,
                  short blocked) {
        this.idclient = idclient;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.doc = doc;
        this.address = address;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.blocked = blocked;
    }

    public short getBlocked() {
        return blocked;
    }

    public void setBlocked(short blocked) {
        this.blocked = blocked;
    }

    public java.sql.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(java.sql.Date birthDate) {
        this.birthDate = birthDate;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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

    public static Client toClient(ClientE clientE){
        return new Client(clientE.getIdclient(),
                clientE.getLastName(),
                clientE.getFirstName(),
                clientE.getPhone(),
                clientE.getBirthDate(),
                clientE.getDoc(),
                clientE.getAddress(),
                clientE.getGender(),
                clientE.getLogin(),
                clientE.getPassword(),
                clientE.getEmail(),
                clientE.getRole(),
                clientE.getBlocked());
    }

    public static ClientE FromClientToClientE(Client client){
        return new ClientE(client.getIdclient(),
                client.getLastName(),
                client.getFirstName(),
                client.getPhone(),
                client.getBirthDate(),
                client.getDoc(),
                client.getAddress(),
                client.getGender(),
                client.getLogin(),
                client.getPassword(),
                client.getEmail(),
                client.getRole(),
                client.getBlocked());
    }
}
