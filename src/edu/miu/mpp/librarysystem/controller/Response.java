/*
    =======================================================================================
    This code is part of mpp project.

    ========================================================================================
    Authors :Cheikh Sad Bouh Ahmed Brahim, Emmanuel Coffie Debrah, Kasaija Ronald, 
    ========================================================================================
*/
package edu.miu.mpp.librarysystem.controller;

import java.util.Objects;

public final class Response {

    private String message;
    private Boolean status;
    private Object data;

    public Response() {

        this.status = false;
    }


    Response( String message, Boolean status, Object data ) {

        this.message = message;
        this.status = status;
        this.data = data;
    }


    public String getMessage() {

        return message;
    }


    public Boolean getStatus() {

        return status;
    }


    public Object getData() {

        return data;
    }


    public void setMessage( String message ) {

        this.message = message;
    }


    public void setStatus( Boolean status ) {

        this.status = status;
    }


    public void setData( Object data ) {

        this.data = data;
    }


    @Override
    public boolean equals( Object obj ) {

        if ( obj == this )
            return true;
        if ( obj == null || obj.getClass() != this.getClass() )
            return false;
        var that = ( Response )obj;
        return Objects.equals( this.message, that.message ) &&
                Objects.equals( this.status, that.status ) &&
                Objects.equals( this.data, that.data );
    }


    @Override
    public int hashCode() {

        return Objects.hash( message, status, data );
    }


    @Override
    public String toString() {

        return "Response[" +
                "message=" + message + ", " +
                "status=" + status + ", " +
                "data=" + data + ", " + ']';
    }

}
