package edu.miu.mpp.librarysystem.dao.model;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {

    private String street;
    private String state;
    private String city;
    private String zip;

    public Address(String street, String state, String city, String zip) {
        this.street = street;
        this.state = state;
        this.city = city;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(state, address.state) && Objects.equals(city, address.city) && Objects.equals(zip, address.zip);
    }



    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
