package edu.miu.mpp.librarysystem.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LibraryMember extends Person implements Serializable {

    private String memberId;
    private List<CheckoutRecord> record;

    public LibraryMember( String memberId, String firstName, String lastName, String phone,
            Address address ) {

        super( firstName, lastName, phone, address );
        this.memberId = memberId;
        this.record = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryMember)) return false;
        if (!super.equals(o)) return false;
        LibraryMember that = (LibraryMember) o;
        return Objects.equals(memberId, that.memberId) && Objects.equals(record, that.record);
    }



    @Override
    public String toString() {
        return "LibraryMember{" +
                "memberId='" + memberId + '\'' +
                ", record=" + record +
                '}';
    }

    public List<CheckoutRecord> getRecord() {
        return record;
    }

    public void setRecord(List<CheckoutRecord> record) {
        this.record = record;
    }

    public String getMemberId() {

        return memberId;
    }


    public void setMemberId( String memberId ) {

        this.memberId = memberId;
    }



}
