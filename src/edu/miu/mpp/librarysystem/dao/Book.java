package edu.miu.mpp.librarysystem.dao;

import java.util.List;

public class Book {

    private String title;
    private String ISBNnumber;
    private String availability;
    private List<BookCopy> bookCopies;
    private List<Author> authors;

    public String getTitle() {

        return title;
    }


    public String getISBNnumber() {

        return ISBNnumber;
    }


    public String getAvailability() {

        return availability;
    }


    public List<BookCopy> getBookCopies() {

        return bookCopies;
    }


    public List<Author> getAuthors() {

        return authors;
    }


    public void setTitle( String title ) {

        this.title = title;
    }


    public void setISBNnumber( String iSBNnumber ) {

        ISBNnumber = iSBNnumber;
    }


    public void setAvailability( String availability ) {

        this.availability = availability;
    }


    public void setBookCopies( List<BookCopy> bookCopies ) {

        this.bookCopies = bookCopies;
    }


    public void setAuthors( List<Author> authors ) {

        this.authors = authors;
    }

}
