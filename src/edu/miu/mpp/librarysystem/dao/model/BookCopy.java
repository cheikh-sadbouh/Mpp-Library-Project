package edu.miu.mpp.librarysystem.dao.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class BookCopy implements Serializable {

    private UUID bookCopyId;
    private Book book;
    private boolean isAvailable = true;

    public BookCopy(UUID bookCopyId, Book book) {
        this.bookCopyId = bookCopyId;
        this.book = book;
    }

    public UUID getBookCopyId() {
        return bookCopyId;
    }

    public Book getBook() {
        return book;
    }

    public  void changeAvailability(){
        this.isAvailable= !isAvailable;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setBookCopyId(UUID bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof BookCopy)) return false;
        BookCopy bookCopy = (BookCopy) o;
        return isAvailable == bookCopy.isAvailable && Objects.equals(bookCopyId, bookCopy.bookCopyId) && Objects.equals(book, bookCopy.book);
    }


    @Override
    public String toString() {
        return "BookCopy{" +
                "bookCopyId=" + bookCopyId +
                ", book=" + book +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
