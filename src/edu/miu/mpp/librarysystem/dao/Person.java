package edu.miu.mpp.librarysystem.dao;

public abstract class Person {

    private String firstName;
    private String lastName;
    private String phone;
    private Address address;
    private String username;
    private String password;

    public Person() {

    }


    public Person( String firstName, String lastName, String phone, String username,
            String password, Address address ) {

        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.username = username;
        this.password = password;
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


    public String getUsername() {

        return username;
    }


    public String getPassword() {

        return password;
    }


    public void setAddress( Address address ) {

        this.address = address;
    }


    public void setUsername( String username ) {

        this.username = username;
    }


    public void setPassword( String password ) {

        this.password = password;
    }

}
