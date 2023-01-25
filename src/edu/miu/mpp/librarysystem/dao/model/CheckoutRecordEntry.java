package edu.miu.mpp.librarysystem.dao.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CheckoutRecordEntry  implements Serializable {
    private BookCopy bookCopy;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    public CheckoutRecordEntry(BookCopy bookCopy){
        this.bookCopy = bookCopy;
        this.checkoutDate = LocalDate.now();
        this.dueDate = this.checkoutDate.plusDays(this.bookCopy.getBook().getMaxBookCheckout().getDays());
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckoutRecordEntry)) return false;
        CheckoutRecordEntry that = (CheckoutRecordEntry) o;
        return Objects.equals(bookCopy, that.bookCopy) && Objects.equals(checkoutDate, that.checkoutDate) && Objects.equals(dueDate, that.dueDate);
    }


    @Override
    public String toString() {
        return "CheckoutRecordEntry{" +
                "book=" + bookCopy +
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

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }


}
