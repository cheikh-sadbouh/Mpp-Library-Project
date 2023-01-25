package edu.miu.mpp.librarysystem.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Author extends Person implements Serializable {

    private String bio;
    private List<Book> books;

    public Author(String bio) {
        this.bio = bio;
        this.books = new ArrayList<>();
    }

    public Author(){}

    public Author(String firstName, String lastName, String phone, Address address, String bio, List<Book> books) {
        super(firstName,lastName,phone,address);
        this.bio = bio;
        this.books = new ArrayList<>();
    }

    public Author(String firstName, String lastName, String phone, Address address, String bio) {
        super(firstName,lastName,phone,address);
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return Objects.equals(bio, author.bio) && Objects.equals(books, author.books);
    }

    @Override
    public String toString() {
        return "Author{" +
                "bio='" + bio + '\'' +
                ", books=" + books +
                '}';
    }
}
