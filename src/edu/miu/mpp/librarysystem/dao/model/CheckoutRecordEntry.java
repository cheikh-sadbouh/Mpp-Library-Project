package edu.miu.mpp.librarysystem.dao.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CheckoutRecordEntry  implements Serializable {
    private BookCopy book;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    public CheckoutRecordEntry(BookCopy book){
        this.book = book;
        this.checkoutDate = LocalDate.now();
        this.dueDate = this.checkoutDate.plusDays(this.book.getBook().getMaxBookCheckout().getDays());
    }

    public void setBook(BookCopy book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckoutRecordEntry)) return false;
        CheckoutRecordEntry that = (CheckoutRecordEntry) o;
        return Objects.equals(book, that.book) && Objects.equals(checkoutDate, that.checkoutDate) && Objects.equals(dueDate, that.dueDate);
    }


    @Override
    public String toString() {
        return "CheckoutRecordEntry{" +
                "book=" + book +
                ", checkoutDate=" + checkoutDate +
                ", dueDate=" + dueDate +
                '}';
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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


}
