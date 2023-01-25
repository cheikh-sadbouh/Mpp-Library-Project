package edu.miu.mpp.librarysystem.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Book implements Serializable {

    private String title;
    private String isbn;
    private List<BookCopy> bookCopies;
    private List<Author> authors;
    private  MaxBookCheckout maxBookCheckout;

    public Book(){}

    public Book(String title, String isbn, List<BookCopy> bookCopies, List<Author> authors, MaxBookCheckout maxBookCheckout) {
        this.title = title;
        this.isbn = isbn;
        this.bookCopies = bookCopies;
        this.authors = authors;
        this.maxBookCheckout = maxBookCheckout;
    }

    public Book(String isbn, String title, MaxBookCheckout maxBookCheckout, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.maxBookCheckout = maxBookCheckout;
        this.authors = authors;
        this.bookCopies = new ArrayList<>();
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
                ", authors=" + authors +
                ", maxBookCheckout=" + maxBookCheckout +
                '}';
    }
}
