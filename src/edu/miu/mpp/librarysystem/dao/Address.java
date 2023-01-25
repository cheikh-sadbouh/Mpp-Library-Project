package edu.miu.mpp.librarysystem.dao;

public class Address {

    private String street;
    private String state;
    private String city;
    private String zip;

    public Address( String street, String city, String state, String zip ) {

        super();
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }


    public String getStreet() {

        return street;
    }


    public String getState() {

        return state;
    }


    public String getCity() {

        return city;
    }


    public String getZip() {

        return zip;
    }


    public void setStreet( String street ) {

        this.street = street;
    }


    public void setState( String state ) {

        this.state = state;
    }


    public void setCity( String city ) {

        this.city = city;
    }


    public void setZip( String zip ) {

        this.zip = zip;
    }

}
