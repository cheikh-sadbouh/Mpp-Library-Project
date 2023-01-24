package edu.miu.mpp.librarysystem.dao;

public enum Role {

    ADMIN( false ),
    LIBRARIAN( false );

    private boolean status;

    private Role( boolean status ) {

        this.status = status;
    }
}
