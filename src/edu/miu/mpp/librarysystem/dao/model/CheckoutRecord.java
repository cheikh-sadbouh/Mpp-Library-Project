package edu.miu.mpp.librarysystem.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CheckoutRecord implements Serializable {
    private  String checkoutId;
    private String libraryMemberId;
    private List<CheckoutRecordEntry> entries;

    public CheckoutRecord(String libraryMemberId) {
        this.libraryMemberId = libraryMemberId;
        this.checkoutId = UUID.randomUUID().toString();
        this.entries = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckoutRecord)) return false;
        CheckoutRecord that = (CheckoutRecord) o;
        return Objects.equals(checkoutId, that.checkoutId) && Objects.equals(libraryMemberId, that.libraryMemberId) && Objects.equals(entries, that.entries);
    }



    @Override
    public String toString() {
        return "CheckoutRecord{" +
                "checkoutId='" + checkoutId + '\'' +
                ", libraryMember=" + libraryMemberId +
                ", entries=" + entries +
                '}';
    }

    public String getCheckoutId() {
        return checkoutId;
    }


    public List<CheckoutRecordEntry> getEntries() {
        return entries;
    }

    public void setCheckoutId(String checkoutId) {
        this.checkoutId = checkoutId;
    }

    public String getLibraryMemberId() {
        return libraryMemberId;
    }

    public void setLibraryMemberId(String libraryMemberId) {
        this.libraryMemberId = libraryMemberId;
    }

    public void setEntries(List<CheckoutRecordEntry> entries) {
        this.entries = entries;
    }
}
