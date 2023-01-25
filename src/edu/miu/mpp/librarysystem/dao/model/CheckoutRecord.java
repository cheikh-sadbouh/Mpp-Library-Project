package edu.miu.mpp.librarysystem.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CheckoutRecord implements Serializable {
    private  String checkoutId;
    private Member libraryMember;
    private List<CheckoutRecordEntry> entries = new ArrayList<>();

    public CheckoutRecord(Member libraryMember) {
        this.libraryMember = libraryMember;
        this.checkoutId= UUID.randomUUID().toString();
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public Member getLibraryMember() {
        return libraryMember;
    }

    public List<CheckoutRecordEntry> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "CheckoutRecord{" +
                "libraryMember=" + libraryMember +
                ", entries=" + entries +
                '}';
    }
}
