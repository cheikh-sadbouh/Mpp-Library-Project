package edu.miu.mpp.librarysystem.dao.model;

import java.util.List;

public class LibraryMember extends Person {

    private String memberId;

    public LibraryMember( String memberId, String firstName, String lastName, String phone,
            Address address ) {

        super( firstName, lastName, phone, address );
        this.memberId = memberId;
    }


    public String getMemberId() {

        return memberId;
    }


    public void setMemberId( String memberId ) {

        this.memberId = memberId;
    }

}
