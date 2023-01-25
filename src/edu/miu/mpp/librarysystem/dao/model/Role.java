package edu.miu.mpp.librarysystem.dao.model;

public enum Role {

    ADMIN( false ),
    LIBRARIAN( false );

    private boolean status;

    private Role( boolean status ) {

        this.status = status;
    }
}
