package edu.miu.mpp.librarysystem.dao.model;

import java.io.Serializable;
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


    public boolean isAvailable() {
        return isAvailable;
    }
}
