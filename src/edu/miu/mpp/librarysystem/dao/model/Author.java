package edu.miu.mpp.librarysystem.dao.model;

import java.util.List;

public class Author extends Person {

    private String bio;
    private List<Book> books;

    public Author( String firstName, String lastName, String phone, Address address, String bio,
            List<Book> books ) {

        super( firstName, lastName, phone, address );
        this.bio = bio;
        this.books = books;
    }

    public Author() {

    }


    public String getBio() {

        return bio;
    }


    public List<Book> getBooks() {

        return books;
    }


    public void setBio( String bio ) {

        this.bio = bio;
    }


    public void setBooks( List<Book> books ) {

        this.books = books;
    }


    @Override
    public String toString() {

        return "Author [bio=" + bio + ", books=" + books.get( 0 ) + "]";
    }

}
