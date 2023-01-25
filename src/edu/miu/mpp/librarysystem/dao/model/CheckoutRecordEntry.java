package edu.miu.mpp.librarysystem.dao.model;


import java.time.LocalDate;

public class CheckoutRecordEntry {
    private BookCopy book;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    public CheckoutRecordEntry(BookCopy book){
        this.book = book;
        this.checkoutDate = LocalDate.now();
        this.dueDate = this.checkoutDate.plusDays(this.book.getBook().getMaxBookCheckout().getDays());
    }

    public BookCopy getBook() {
        return book;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "CheckoutRecordEntry{" +
                "book=" + book +
                ", checkoutDate=" + checkoutDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
