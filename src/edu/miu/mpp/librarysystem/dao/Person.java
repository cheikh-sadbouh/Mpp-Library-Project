package edu.miu.mpp.librarysystem.dao;

public abstract class Person {

    private String firstName;
    private String lastName;
    private String phone;
    private Address address;

    public Person() {

    }


    public Person( String firstName, String lastName, String phone, Address address ) {

        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }


    public String getFirstName() {

        return firstName;
    }


    public String getLastName() {

        return lastName;
    }


    public String getPhone() {

        return phone;
    }


    public void setFirstName( String firstName ) {

        this.firstName = firstName;
    }


    public void setLastName( String lastName ) {

        this.lastName = lastName;
    }


    public void setPhone( String phone ) {

        this.phone = phone;
    }


    public Address getAddress() {

        return address;
    }

}
