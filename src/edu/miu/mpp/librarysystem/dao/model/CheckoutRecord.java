package edu.miu.mpp.librarysystem.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CheckoutRecord implements Serializable {
    private  String checkoutId;
    private LibraryMember libraryMember;
    private List<CheckoutRecordEntry> entries = new ArrayList<>();

    public CheckoutRecord(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
        this.checkoutId= UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckoutRecord)) return false;
        CheckoutRecord that = (CheckoutRecord) o;
        return Objects.equals(checkoutId, that.checkoutId) && Objects.equals(libraryMember, that.libraryMember) && Objects.equals(entries, that.entries);
    }



    @Override
    public String toString() {
        return "CheckoutRecord{" +
                "checkoutId='" + checkoutId + '\'' +
                ", libraryMember=" + libraryMember +
                ", entries=" + entries +
                '}';
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public List<CheckoutRecordEntry> getEntries() {
        return entries;
    }

    public void setCheckoutId(String checkoutId) {
        this.checkoutId = checkoutId;
    }

    public void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    public void setEntries(List<CheckoutRecordEntry> entries) {
        this.entries = entries;
    }
}
