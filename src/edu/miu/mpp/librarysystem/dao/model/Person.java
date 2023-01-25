package edu.miu.mpp.librarysystem.dao.model;

import edu.miu.mpp.librarysystem.dao.model.Address;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person  implements Serializable {

    private String firstName;
    private String lastName;
    private String phone;
    private Address address;

    public Person() {

    }


    public void setAddress(Address address) {
        this.address = address;
    }

    public Person(String firstName, String lastName, String phone, Address address ) {

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

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(phone, person.phone) && Objects.equals(address, person.address);
    }


}
