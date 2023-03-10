package edu.miu.mpp.librarysystem.dao.model;

import java.io.Serializable;
import java.util.*;

public class Book implements Serializable {

    private String title;
    private String isbn;
    private List<BookCopy> bookCopies;
    private List<Author> authors;
    private  MaxBookCheckout maxBookCheckout;

    public Book(){}

    public Book(String isbn, String title, MaxBookCheckout maxBookCheckout, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.maxBookCheckout = maxBookCheckout;
        this.authors = authors;
        if(maxBookCheckout.equals(MaxBookCheckout.TWENTY_ONE_DAYS))
             this.bookCopies = new ArrayList<>(Arrays.asList(new BookCopy(UUID.randomUUID(),this)));
        else
            this.bookCopies = new ArrayList<>(Arrays.asList(new BookCopy(UUID.randomUUID(),this),new BookCopy(UUID.randomUUID(),this)));
    }

    public Book(String isbn, String title, MaxBookCheckout maxBookCheckout, List<Author> authors, Integer numberOfCopies){
        this.isbn = isbn;
        this.title = title;
        this.maxBookCheckout = maxBookCheckout;
        this.authors = authors;
        this.bookCopies = new ArrayList<>();
        for (int i = 1; i <  numberOfCopies; i++) {
            this.bookCopies.add(new BookCopy(UUID.randomUUID(),this));
        }
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public void setBookCopies(List<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public MaxBookCheckout getMaxBookCheckout() {
        return maxBookCheckout;
    }

    public void setMaxBookCheckout(MaxBookCheckout maxBookCheckout) {
        this.maxBookCheckout = maxBookCheckout;
    }

    public void addCopy() {
        bookCopies.add(new BookCopy(UUID.randomUUID(),this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn) && Objects.equals(bookCopies, book.bookCopies) && Objects.equals(authors, book.authors) && maxBookCheckout == book.maxBookCheckout;
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", bookCopies=" + bookCopies +
                ", authors=" + authors +
                ", maxBookCheckout=" + maxBookCheckout +
                '}';
    }
}
